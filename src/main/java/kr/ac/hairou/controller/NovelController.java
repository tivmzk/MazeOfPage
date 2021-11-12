package kr.ac.hairou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hairou.model.Bookmark;
import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.Member;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.model.Recommend;
import kr.ac.hairou.model.Thumbnail;
import kr.ac.hairou.service.BookmarkService;
import kr.ac.hairou.service.EpisodeService;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.service.RecommendService;
import kr.ac.hairou.service.ThumbnailService;
import kr.ac.hairou.util.FileManager;
import kr.ac.hairou.util.Pager;
import kr.ac.hairou.util.PreviewManager;
import kr.ac.hairou.util.ThumbnailManager;

@Controller
@RequestMapping("/novel")
public class NovelController {
	@Autowired
	NovelService service;
	@Autowired
	GenreService genreService;
	@Autowired
	RecommendService recomService;
	@Autowired
	BookmarkService bookmarkService;
	@Autowired
	ThumbnailService thumbnailService;
	@Autowired
	EpisodeService episodeService;
	
	private final String PATH = "novel/";
	
	@GetMapping("/list")
	public String list(Model model, Pager pager) {
		List<Novel> novelList = service.getList(pager);
		model.addAttribute("list", novelList);
		model.addAttribute("pager", pager);
		return PATH+"list.main";
	}
	
	@GetMapping("/add")
	public String add(Model model, Pager pager) {
		
		int total = genreService.getTotal();
		pager.setTotal(total);
		pager.setPerPage(total);
		List<Genre> list = genreService.getList(pager);
		model.addAttribute("list", list);
		return PATH+"add.main";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces="application/text;charset=utf8")
	public String add(Novel item, @RequestParam("thumbnail") MultipartFile image) {
		
		Thumbnail thumbnail = new Thumbnail();
		FileManager manager = null;
		try {
			if(image.isEmpty() || image == null) {
				manager = new PreviewManager();
				thumbnail = manager.upload();
			}
			else {
				manager = new ThumbnailManager(image);
				thumbnail = manager.upload();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		item.setImage(thumbnail);
		
		service.add(item);
		
		return "redirect:detail/"+item.getCode();
	}
	
	@GetMapping("/detail/{code}")
	public String detail(@PathVariable int code, Model model, Pager pager, HttpSession session) {
		Novel item = service.getItem(code);
		Member user = (Member) session.getAttribute("user");
		
		if(user != null) {
			Recommend recom = recomService.getItem(user.getId(), code);
			Bookmark bookmark = bookmarkService.getItem(user.getId(), code);
			
			model.addAttribute("recom", recom);
			model.addAttribute("bookmark", bookmark);
		}
		
		pager.setKeyword(item.getNickname());
		pager.setKeyword2(""+code);
		pager.setSearch(4);
		pager.setOrder(1);
		pager.setPerPage(5);
		List<Novel> userList = service.getList(pager);
		
		model.addAttribute("item", item);
		model.addAttribute("userList", userList);
		pager.reset();
		return PATH+"detail.main";
	}
	
	@GetMapping("/delete/{code}")
	public String delete(@PathVariable int code) {
		try {
			Thumbnail item = thumbnailService.getItem(code);
			
			service.delete(code);
			FileManager.delete(item);
		}
		catch(Exception e) {
			System.out.println("썸네일 삭제에 실패했습니다");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/update/{code}")
	public String update(@PathVariable int code, Model model, Pager pager) {
		int total = genreService.getTotal();
		pager.setTotal(total);
		pager.setPerPage(total);
		
		Novel item = service.getItem(code);
		List<Genre> list = genreService.getList(pager);
		String info = item.getInfo();
		item.setInfo(info.replaceAll("<br>", "\n"));
		
		model.addAttribute("novel", item);
		model.addAttribute("list", list);
		
		return PATH+"update.main";
	}
	
	@RequestMapping(value="/update/{code}", method = RequestMethod.POST, produces="application/text;charset=utf8")
	public String update(@PathVariable int code, Novel novel, @RequestParam("thumbnail") MultipartFile image) {
		novel.setCode(code);
		Thumbnail thumbnail = new Thumbnail();
		FileManager manager = null;
		
		try {
			if(!image.isEmpty() && image != null) {
				Thumbnail item = thumbnailService.getItem(code);
				thumbnailService.delete(code);
				FileManager.delete(item);
				manager = new ThumbnailManager(image);
				thumbnail = manager.upload();
				novel.setImage(thumbnail);
			}

			service.update(novel);
		}
		catch(NullPointerException e) {
			manager = new ThumbnailManager(image);
			try {
				thumbnail = manager.upload();
				novel.setImage(thumbnail);
				service.update(novel);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/novel/detail/"+code;
	}
}

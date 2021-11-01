package kr.ac.hairou.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.model.Thumbnail;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.util.Pager;

@Controller
@RequestMapping("/novel")
public class NovelController {
	private final String UPLOAD_PATH = "D:/thumbnail/";
	@Autowired
	NovelService service;
	@Autowired
	GenreService genreService;
	
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
		
		try {
			if(image.isEmpty() || image == null) {
				thumbnail.setFilename("preview.jpg");
				UUID uuid = UUID.randomUUID();
				thumbnail.setUuid(uuid.toString());
				int rand = (int)(Math.random()*17);
				File file = new File(UPLOAD_PATH+"preview"+rand+".jpg");
				File newFile = new File(UPLOAD_PATH+thumbnail.getFullname());
				Files.copy(file, newFile);
				if(!newFile.createNewFile()) {
					System.out.println("파일이 이미 존재합니다.");
				}
			}
			else {
				thumbnail.setFilename(image.getOriginalFilename());
				UUID uuid = UUID.randomUUID();
				thumbnail.setUuid(uuid.toString());
				image.transferTo(new File(UPLOAD_PATH+thumbnail.getFullname()));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		item.setImage(thumbnail);
		
		service.add(item);
		
		return "redirect:/";
	}
}

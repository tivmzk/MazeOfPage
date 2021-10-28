package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.Member;
import kr.ac.hairou.model.Notice;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.service.MemberService;
import kr.ac.hairou.service.NoticeService;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.util.SearchOption;

@Controller
public class RootController {
	@Autowired
	NovelService novelService;
	@Autowired
	GenreService genreService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String index(Model model) {
		try {
			List<Novel> rankingList = novelService.getRanking(new SearchOption());
			List<Genre> genreList = genreService.getList(new SearchOption(1));
			List<Notice> noticeList = noticeService.getList();
			List<Novel> genreRankList = novelService.getRanking(new SearchOption(1, genreList.get(0).getCode()));
			
			model.addAttribute("novelList", rankingList);
			model.addAttribute("genreList", genreList);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("genreNovelList", genreRankList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index.main";
	}
	
	@GetMapping("/dummy")
	public String dummy(){
		List<Genre> list = genreService.getList(new SearchOption());
		for(Genre item : list) {
			novelService.dummy(item.getCode());
		}
		
		return "redirect:.";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.main";
	}
	
	@GetMapping("/signup")
	public String signup(){
		return "signup.main";
	}
	
	@PostMapping("/signup")
	public String signup(Member item){
		memberService.add(item);
		return "redirect:login";
	}
	
	@ResponseBody
	@GetMapping("/check_nickname/{nickname}")
	public boolean checkNickname(@PathVariable String nickname){
		SearchOption option = new SearchOption(1);
		option.setKeyword(nickname);
		List<Member> list = memberService.getList(option);
		
		if(list.size() < 1)
			return true;
		else
			return false;
	}
	
	@ResponseBody
	@GetMapping("/check_id/{id}")
	public boolean checkId(@PathVariable String id){
		Member item = memberService.getItem(id);
		
		if(item == null)
			return true;
		else
			return false;
	}
}

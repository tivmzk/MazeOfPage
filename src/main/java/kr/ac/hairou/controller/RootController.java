package kr.ac.hairou.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.GenreRank;
import kr.ac.hairou.model.Member;
import kr.ac.hairou.model.Notice;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.service.MemberService;
import kr.ac.hairou.service.NoticeService;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.util.Pager;

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
	public String index(Model model, Pager pager) {
		try {
			pager.setPerPage(5);
			pager.setOrder(1);
			List<Novel> rankingList = novelService.getList(pager);
			pager.setPerPage(2);
			pager.setOrder(0);
			List<Novel> novelList = novelService.getList(pager);
			pager.setPerPage(3);
			List<Genre> genreList = genreService.getList(pager);
			pager.setPerPage(5);
			List<Notice> noticeList = noticeService.getList(pager);
			List<GenreRank> genreRankList = new ArrayList<>();
			
			for(Genre genre : genreList) {
				pager.setPerPage(5);
				pager.setOrder(1);
				pager.setKeyword(genre.getContents());
				pager.setSearch(3);
				List<Novel> list = novelService.getList(pager);
				genreRankList.add(new GenreRank(genre.getContents(), list));
			}
			
			model.addAttribute("rankingList", rankingList);
			model.addAttribute("novelList", novelList);
			model.addAttribute("genreList", genreList);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("genreRankList", genreRankList);
			pager.reset();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index.main";
	}
	
	@GetMapping("/dummy")
	public String dummy(){
		List<Genre> list = genreService.getList(new Pager());
		for(Genre item : list) {
			novelService.dummy(item.getCode());
		}
		
		return "redirect:.";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.main";
	}
	
	@PostMapping("/login")
	public String login(Member member, HttpSession session) {
		Member item = memberService.getItem(member);
		
		if(item == null) {
			return "login.main";
		}
		
		session.setAttribute("user", item);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:.";
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
		Pager pager = new Pager();
		pager.setKeyword(nickname);
		pager.setSearch(1);
		List<Member> list = memberService.getList(pager);
		
		if(list.size() < 1)
			return true;
		else
			return false;
	}
	
	@ResponseBody
	@GetMapping("/check_id/{id}")
	public boolean checkId(@PathVariable String id){
		Pager pager = new Pager();
		pager.setKeyword(id);
		pager.setSearch(2);
		List<Member> list = memberService.getList(pager);
		
		if(list.size() < 1)
			return true;
		else
			return false;
	}
}

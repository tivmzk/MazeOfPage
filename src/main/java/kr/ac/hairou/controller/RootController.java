package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.Notice;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.service.GenreService;
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
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Novel> rankingList = novelService.getRanking(new SearchOption());
		List<Genre> genreList = genreService.getList();
		List<Notice> noticeList = noticeService.getList();
		List<Novel> genreRankList = novelService.getRanking(new SearchOption(1, genreList.get(0).getCode()));
		
		model.addAttribute("novelList", rankingList);
		model.addAttribute("genreList", genreList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("genreNovelList", genreRankList);
		
		return "index.main";
	}
	
	@GetMapping("/dummy")
	public String dummy(){
		for(int i = 1; i < 11; i++)
			novelService.dummy(i);
		return "redirect:.";
	}
}

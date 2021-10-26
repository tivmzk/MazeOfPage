package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.Notice;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.service.NoticeService;
import kr.ac.hairou.service.NovelService;

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
		List<Novel> rankingList = novelService.getRanking();
		List<Genre> genreList = genreService.getList();
		List<Notice> noticeList = noticeService.getList();
		
		model.addAttribute("novelList", rankingList);
		model.addAttribute("genreList", genreList);
		model.addAttribute("noticeList", noticeList);
		
		return "index.main";
	}
}

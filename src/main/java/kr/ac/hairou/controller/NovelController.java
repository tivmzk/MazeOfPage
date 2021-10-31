package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.util.Pager;

@Controller
@RequestMapping("/novel")
public class NovelController {
	@Autowired
	NovelService service;
	
	private final String PATH = "novel/";
	
	@GetMapping("/list")
	public String list(Model model, Pager pager) {
		List<Novel> novelList = service.getList(pager);
		model.addAttribute("list", novelList);
		model.addAttribute("pager", pager);
		return PATH+"list.main";
	}
}

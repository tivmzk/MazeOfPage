package kr.ac.hairou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.service.NovelService;

@Controller
@RequestMapping("/novel")
public class NovelController {
	@Autowired
	NovelService service;
	
	private final String PATH = "novel/";
	
	@GetMapping("/list")
	public String list() {
		System.out.println("list");
		return PATH+"list.main";
	}
}

package kr.ac.hairou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.service.EpisodeService;

@RequestMapping("/episode")
@Controller
public class EpisodeController {
	private final String PATH = "episode/";
	
	@Autowired
	EpisodeService epiService;
	
	@GetMapping("/add")
	public String add() {
		return PATH+"add.main";
	}
}

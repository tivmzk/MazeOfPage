package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.util.Pager;

@Controller
@RequestMapping("/genre")
public class GenreController {
	private final String PATH = "genre/";
	@Autowired
	GenreService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		Pager pager = new Pager();
		pager.setPerPage(service.getTotal());
		List<Genre> list = service.getList(pager);
		model.addAttribute("list", list);
		return PATH+"list.main";
	}
}

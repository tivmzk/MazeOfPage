package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.service.NoticeService;
import kr.ac.hairou.util.Pager;

@RequestMapping("/notice")
@Controller
public class NoticeController {
	private final String PATH = "notice/";
	@Autowired
	NoticeService service;
	
	@GetMapping("/list")
	public String list(Model model, Pager pager) {
		List<Notice> list = service.getList(pager);
		model.addAttribute("list", list);
		return PATH+"list.main";
	}
	
	@GetMapping("/add")
	public String add() {
		return PATH+"add.main";
	}
	
	@PostMapping("/add")
	public String add(Notice item) {
		service.add(item);
		return "redirect:list";
	}
}

package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/view/{code}")
	public String view(@PathVariable int code, Model model) {
		Notice item = service.getItem(code);
		model.addAttribute("item", item);
		return PATH+"view.main";
	}
	
	@GetMapping("/delete/{code}")
	public String delete(@PathVariable int code) {
		service.delete(code);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/update/{code}")
	public String update(@PathVariable int code, Model model) {
		Notice item = service.getItem(code);
		item.setContents(item.getContents().replace("<br>", "\n"));
		model.addAttribute("item", item);
		return PATH+"update.main";
	}
	
	@PostMapping("/update/{code}")
	public String update(@PathVariable int code, Notice item) {
		item.setCode(code);
		service.update(item);
		return "redirect:/notice/list";
	}
}

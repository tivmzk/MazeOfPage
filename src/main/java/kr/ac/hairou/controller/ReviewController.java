package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Review;
import kr.ac.hairou.service.ReviewService;
import kr.ac.hairou.util.Pager;

@RequestMapping("/review")
@Controller
public class ReviewController {
	private final String PATH = "review/";
	@Autowired
	ReviewService service;
	
	@GetMapping("/list")
	public String list(Model model, Pager pager) {
		List<Review> list = service.getList(pager);
		model.addAttribute("list", list);
		return PATH+"list.main";
	}
	
	@GetMapping("/add")
	public String add(int novel, Model model) {
		model.addAttribute("novel", novel);
		return PATH+"add.main";
	}
	
	@PostMapping("/add")
	public String add(Review item) {
		service.add(item);
		return "redirect:list";
	}
	
	@GetMapping("/update/{code}")
	public String update(@PathVariable int code, Model model) {
		Review item = service.getItem(code);
		model.addAttribute("item", item);
		return PATH+"update.main";
	}
	
	@PostMapping("/update/{code}")
	public String update(@PathVariable int code, Review item) {
		item.setCode(code);
		service.update(item);
		return "redirect:../list";
	}
	
	@GetMapping("/delete/{code}")
	public String delete(@PathVariable int code) {
		service.delete(code);
		return "redirect:../list";
	}
	
	@GetMapping("/view/{code}")
	public String view(@PathVariable int code, Model model) {
		Review item = service.getItem(code);
		model.addAttribute("item", item);
		return PATH+"view.main";
	}
}

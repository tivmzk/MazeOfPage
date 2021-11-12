package kr.ac.hairou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hairou.model.Profile;
import kr.ac.hairou.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	private final String PATH = "profile/";
	@Autowired
	ProfileService service;
	
	@GetMapping("/{member}")
	public String profile(@PathVariable String member, Model model) {
		Profile item = service.getItem(member);
		model.addAttribute("item", item);
		return PATH+"view.main";
	}
	
	@PostMapping("/{member}")
	public String update(@PathVariable String member, Profile item) {
		item.setMember(member);
		service.update(item);
		return "redirect:"+member;
	}
}

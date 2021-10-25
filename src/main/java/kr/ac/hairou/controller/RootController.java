package kr.ac.hairou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	@RequestMapping("/")
	public String index() {
		System.out.println("asdf");
		return "index";
	}
}

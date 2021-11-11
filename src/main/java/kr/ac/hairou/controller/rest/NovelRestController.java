package kr.ac.hairou.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.util.Pager;

@RestController
@RequestMapping("/rest/novel")
public class NovelRestController {
	@Autowired
	NovelService service;
	
	@GetMapping
	public Map<String, Object> list(Pager pager){
		Map<String, Object> map = new HashMap<>();
		
		List<Novel> list = service.getList(pager);
		
		map.put("list", list);
		map.put("pager", pager);
		
		return map;
	}
}

package kr.ac.hairou.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Recommend;
import kr.ac.hairou.service.RecommendService;

@RequestMapping("/api/recom")
@RestController
public class RecommendController {
	@Autowired
	RecommendService recomService;
	
	@Transactional
	@PostMapping
	public int add(@RequestBody Recommend item) {
		recomService.add(item);
		return recomService.getTotal(item.getNovel());
	}
	
	@Transactional
	@DeleteMapping
	public int delete(@RequestBody Recommend item) {
		recomService.delete(item);
		return recomService.getTotal(item.getNovel());
	}
	
	@GetMapping
	public boolean check(Recommend item) {
		Recommend recom = recomService.getItem(item.getMember(), item.getNovel());
		if(recom == null) {
			return false;
		}
		else {
			return true;
		}
	}
}

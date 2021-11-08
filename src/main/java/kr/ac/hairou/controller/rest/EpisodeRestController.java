package kr.ac.hairou.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Episode;
import kr.ac.hairou.service.EpisodeService;
import kr.ac.hairou.util.Pager;

@RequestMapping("/rest/episode")
@RestController
public class EpisodeRestController {
	
	@Autowired
	EpisodeService episodeService;
	
	@GetMapping("/all")
	public List<Episode> listAll(Pager pager){
		int total = episodeService.getTotal(Integer.parseInt(pager.getKeyword()));
		pager.setPerPage(total);
		return episodeService.getList(pager);
	}
	
	@GetMapping
	public List<Episode> list(Pager pager){
		return episodeService.getList(pager);
	}
	
	@GetMapping("/item")
	public Episode item(Pager pager) {
		return episodeService.getItem(pager);
	}
	
	@DeleteMapping
	public int delete(int code) {
		episodeService.delete(code);
		return code;
	}
}

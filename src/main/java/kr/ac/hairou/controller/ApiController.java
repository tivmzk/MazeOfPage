package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Bookmark;
import kr.ac.hairou.model.Episode;
import kr.ac.hairou.model.Recommend;
import kr.ac.hairou.service.BookmarkService;
import kr.ac.hairou.service.EpisodeService;
import kr.ac.hairou.service.OptionService;
import kr.ac.hairou.service.RecommendService;
import kr.ac.hairou.util.Pager;

@RequestMapping("/api")
@RestController
public class ApiController {
	@Autowired
	RecommendService recomService;
	@Autowired
	BookmarkService bookmarkService;
	@Autowired
	EpisodeService episodeService;
	@Autowired
	OptionService optionService;
	
	@Transactional
	@PostMapping("/recom")
	public int recommendAdd(@RequestBody Recommend item) {
		recomService.add(item);
		return recomService.getTotal(item.getNovel());
	}
	
	@Transactional
	@DeleteMapping("/recom")
	public int recommendDelete(@RequestBody Recommend item) {
		recomService.delete(item);
		return recomService.getTotal(item.getNovel());
	}
	
	@GetMapping("/recom")
	public boolean recommendCheck(Recommend item) {
		Recommend recom = recomService.getItem(item.getMember(), item.getNovel());
		if(recom == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Transactional
	@PostMapping("/bookmark")
	public int bookmarkAdd(@RequestBody Bookmark item) {
		bookmarkService.add(item);
		return bookmarkService.getTotal(item.getNovel());
	}
	
	@Transactional
	@DeleteMapping("/bookmark")
	public int bookmarkDelete(@RequestBody Bookmark item) {
		bookmarkService.delete(item);
		return bookmarkService.getTotal(item.getNovel());
	}
	
	@GetMapping("/bookmark")
	public boolean bookmarkCheck(Bookmark item) {
		Bookmark bookmark = bookmarkService.getItem(item.getMember(), item.getNovel());
		if(bookmark == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@GetMapping("/episode")
	public List<Episode> episodeList(Pager pager){
		int total = episodeService.getTotal(Integer.parseInt(pager.getKeyword()));
		pager.setPerPage(total);
		return episodeService.getList(pager);
	}
}

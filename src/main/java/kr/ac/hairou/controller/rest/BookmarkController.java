package kr.ac.hairou.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Bookmark;
import kr.ac.hairou.service.BookmarkService;

@RequestMapping("/rest/bookmark")
@RestController
public class BookmarkController {
	@Autowired
	BookmarkService bookmarkService;
	
	@Transactional
	@PostMapping
	public int add(@RequestBody Bookmark item) {
		bookmarkService.add(item);
		return bookmarkService.getTotal(item.getNovel());
	}
	
	@Transactional
	@DeleteMapping
	public int delete(@RequestBody Bookmark item) {
		bookmarkService.delete(item);
		return bookmarkService.getTotal(item.getNovel());
	}
	
	@GetMapping
	public boolean check(Bookmark item) {
		Bookmark bookmark = bookmarkService.getItem(item.getMember(), item.getNovel());
		
		if(bookmark == null) {
			return false;
		}
		else {
			return true;
		}
	}
}

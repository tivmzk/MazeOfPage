package kr.ac.hairou.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Comments;
import kr.ac.hairou.service.CommentsService;
import kr.ac.hairou.util.Pager;

@RequestMapping("/rest/comments")
@RestController
public class CommentsController {
	@Autowired
	CommentsService service;
	
	@PostMapping
	public Comments add(@RequestBody Comments item) {
		Comments comments = service.add(item);
		return comments;
	}
	
	@GetMapping
	public List<Comments> list(Pager pager){
		List<Comments> list = service.getList(pager);
		return list;
	}
	
	@DeleteMapping
	public int delete(int code) {
		service.delete(code);
		return code;
	}
	
	@PutMapping
	public Comments update(@RequestBody Comments item) {
		service.update(item);
		return item;
	}
}

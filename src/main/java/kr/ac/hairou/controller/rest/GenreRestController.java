package kr.ac.hairou.controller.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.service.GenreService;

@RequestMapping("/rest/genre")
@RestController
public class GenreRestController {
	@Autowired
	GenreService service;
	
	@PostMapping
	public Genre add(@RequestBody Genre item) {
		Genre genre = service.add(item);
		return genre;
	}
	
	@DeleteMapping
	public Map<String, Object> delete(int code) {
		Map<String, Object> map = service.delete(code);
		return map;
	}
}

package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.GenreDao;
import kr.ac.hairou.model.Genre;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	GenreDao dao;
	
	@Override
	public List<Genre> getList() {
		return dao.getList();
	}

}

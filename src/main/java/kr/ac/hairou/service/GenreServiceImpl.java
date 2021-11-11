package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hairou.dao.GenreDao;
import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.Pager;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	GenreDao dao;
	
	@Override
	public List<Genre> getList(Pager pager) {
		return dao.getList(pager);
	}

	@Override
	public int getTotal() {
		return dao.getToal();
	}
	
	@Transactional
	@Override
	public Genre add(Genre item) {
		dao.add(item);
		return dao.getItem(item.getCode());
	}

}

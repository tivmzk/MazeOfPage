package kr.ac.hairou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.BookmarkDao;
import kr.ac.hairou.model.Bookmark;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	@Autowired
	BookmarkDao dao;
	
	@Override
	public Bookmark getItem(String member, int novel) {
		return dao.getItem(member, novel);
	}

	@Override
	public void add(Bookmark item) {
		dao.add(item);
	}

	@Override
	public int getTotal(int novel) {
		return dao.getTotal(novel);
	}

	@Override
	public void delete(Bookmark item) {
		dao.delete(item);
	}
}

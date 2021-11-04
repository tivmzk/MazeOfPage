package kr.ac.hairou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.ThumbnailDao;
import kr.ac.hairou.model.Thumbnail;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {
	@Autowired
	ThumbnailDao dao;
	
	@Override
	public Thumbnail getItem(int code) {
		return dao.getItem(code);
	}

	@Override
	public void delete(int code) {
		dao.delete(code);
	}
	
}

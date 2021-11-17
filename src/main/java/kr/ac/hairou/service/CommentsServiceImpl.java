package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.CommentsDao;
import kr.ac.hairou.model.Comments;
import kr.ac.hairou.util.Pager;

@Service
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	CommentsDao dao;
	
	@Override
	public Comments add(Comments item) {
		dao.add(item);
		return dao.getItem(item.getCode());
	}

	@Override
	public List<Comments> getList(Pager pager) {
		return dao.getList(pager);
	}
	
	@Override
	public void delete(int code) {
		dao.delete(code);
	}
	
	@Override
	public void update(Comments item) {
		dao.update(item);
	}
}

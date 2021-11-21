package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hairou.dao.CommentsDao;
import kr.ac.hairou.dao.ReviewDao;
import kr.ac.hairou.model.Comments;
import kr.ac.hairou.model.Review;
import kr.ac.hairou.util.Pager;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewDao dao;
	@Autowired
	CommentsDao commentsDao;
	
	@Override
	public List<Review> getList(Pager pager) {
		int total = dao.getTotal(pager);
		pager.setTotal(total);
		return dao.getList(pager);
	}

	@Override
	public void add(Review item) {
		dao.add(item);
	}

	@Override
	public Review getItem(int code) {
		return dao.getItem(code);
	}

	@Override
	public void update(Review item) {
		dao.update(item);
	}
	
	@Transactional
	@Override
	public void delete(int code) {
		Pager pager = new Pager();
		pager.setKeyword(""+code);
		List<Comments> list = commentsDao.getList(pager);
		for(Comments item : list) {
			commentsDao.delete(item.getCode());
		}
		dao.delete(code);
	}

}

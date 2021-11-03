package kr.ac.hairou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.RecommendDao;
import kr.ac.hairou.model.Recommend;

@Service
public class RecommendServiceImpl implements RecommendService {
	@Autowired
	RecommendDao dao;
	
	@Override
	public Recommend getItem(String member, int novel) {
		return dao.getItem(member, novel);
	}

	@Override
	public void add(Recommend item) {
		dao.add(item);
	}

	@Override
	public int getTotal(int novel) {
		return dao.getTotal(novel);
	}

	@Override
	public void delete(Recommend item) {
		dao.delete(item);
	}
}

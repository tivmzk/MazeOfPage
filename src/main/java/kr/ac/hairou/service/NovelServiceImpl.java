package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.NovelDao;
import kr.ac.hairou.model.Novel;

@Service
public class NovelServiceImpl implements NovelService {
	@Autowired
	NovelDao dao;
	
	@Override
	public List<Novel> getRanking() {
		List<Novel> list = dao.getRanking();
		for(int i = 1; i <= list.size(); i++) {
			list.get(i - 1).setRank(i);
		}
		return list;
	}

}

package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.NovelDao;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.Pager;

@Service
public class NovelServiceImpl implements NovelService {
	@Autowired
	NovelDao dao;

	@Override
	public void dummy(int genre) {
		for(int i = 0; i < 10; i++) {
			Novel item = new Novel();
			item.setMember("user123");
			item.setGenre(genre);
			item.setTitle(String.format("장르 %d 소설", genre));
			item.setInfo(String.format("장르 %d 소설의 내용", genre));
			item.setRecom((int)(Math.random() * 1000));
			
			dao.add(item);
		}
	}

	@Override
	public List<Novel> getList(Pager pager) {
		int total = dao.getTotal(pager);
		pager.setTotal(total);
		return dao.getList(pager);
	}

}

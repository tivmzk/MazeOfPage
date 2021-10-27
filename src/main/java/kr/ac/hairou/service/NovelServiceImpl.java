package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.NovelDao;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.SearchOption;

@Service
public class NovelServiceImpl implements NovelService {
	@Autowired
	NovelDao dao;

	@Override
	public List<Novel> getRanking(SearchOption searchOption) {
		List<Novel> list = dao.getRanking(searchOption);
		for(int i = 1; i <= list.size(); i++) {
			list.get(i - 1).setRank(i);
		}
		return list;
	}

	@Override
	public void dummy(int genre) {
		for(int i = 0; i < 10; i++) {
			Novel item = new Novel();
			item.setMember("user");
			item.setGenre(genre);
			item.setTitle(String.format("장르 %d 소설", genre));
			item.setInfo(String.format("장르 %d 소설의 내용", genre));
			item.setRecom((int)(Math.random() * 1000));
			
			dao.add(item);
		}
	}

}

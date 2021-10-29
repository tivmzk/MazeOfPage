package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.SearchOption;

public interface NovelService {

	List<Novel> getRanking(SearchOption searchOption);
	
	void dummy(int genre);

	List<Novel> getList(SearchOption option);
}

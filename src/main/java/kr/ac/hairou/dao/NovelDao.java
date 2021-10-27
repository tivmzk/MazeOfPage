package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.SearchOption;

public interface NovelDao {

	List<Novel> getRanking(SearchOption searchOption);

	void add(Novel item);

}

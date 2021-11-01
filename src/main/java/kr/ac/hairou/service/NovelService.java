package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.Pager;

public interface NovelService {
	void dummy(int genre);
	List<Novel> getList(Pager pager);
	void add(Novel item);
}

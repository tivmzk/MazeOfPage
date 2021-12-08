package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Member;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.Pager;

public interface NovelService {
	void dummy(Member member);
	List<Novel> getList(Pager pager);
	void add(Novel item);
	Novel getItem(int code);
	void delete(int code);
	void update(Novel item);
}

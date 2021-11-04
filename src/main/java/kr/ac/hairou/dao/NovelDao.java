package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.Pager;

public interface NovelDao {
	void add(Novel item);

	List<Novel> getList(Pager pager);

	int getTotal(Pager pager);

	Novel getItem(int code);

	void delete(int code);

	void update(Novel item);
}

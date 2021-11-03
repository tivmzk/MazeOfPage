package kr.ac.hairou.dao;

import kr.ac.hairou.model.Recommend;

public interface RecommendDao {

	Recommend getItem(String member, int novel);

	void add(Recommend item);

	int getTotal(int novel);

	void delete(Recommend item);

}

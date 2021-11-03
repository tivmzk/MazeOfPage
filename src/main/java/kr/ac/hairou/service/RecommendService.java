package kr.ac.hairou.service;

import kr.ac.hairou.model.Recommend;

public interface RecommendService {

	Recommend getItem(String member, int novel);

	void add(Recommend item);

	int getTotal(int novel);

	void delete(Recommend item);

}

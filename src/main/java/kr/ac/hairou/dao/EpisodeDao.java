package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Episode;
import kr.ac.hairou.util.Pager;

public interface EpisodeDao {

	int getTotal(int code);

	List<Episode> getList(Pager pager);

	Episode getItem(Pager pager);

	void add(Episode item);

	void delete(int code);

}

package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.Pager;

public interface GenreDao {

	List<Genre> getList(Pager pager);

	int getToal();

	void add(Genre item);

	Genre getItem(int code);

}

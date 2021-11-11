package kr.ac.hairou.service;

import java.util.List;
import java.util.Map;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.Pager;

public interface GenreService {

	List<Genre> getList(Pager pager);

	int getTotal();

	Genre add(Genre item);

	Map<String, Object> delete(int code);

}

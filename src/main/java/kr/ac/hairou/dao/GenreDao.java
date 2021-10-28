package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.SearchOption;

public interface GenreDao {

	List<Genre> getList(SearchOption option);

}

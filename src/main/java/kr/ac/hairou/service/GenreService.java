package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.SearchOption;

public interface GenreService {

	List<Genre> getList(SearchOption option);

}

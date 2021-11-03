package kr.ac.hairou.service;

import kr.ac.hairou.model.Bookmark;

public interface BookmarkService {

	Bookmark getItem(String member, int novel);

	void add(Bookmark item);

	int getTotal(int novel);

	void delete(Bookmark item);

}

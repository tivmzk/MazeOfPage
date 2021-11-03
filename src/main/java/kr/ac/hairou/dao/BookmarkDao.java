package kr.ac.hairou.dao;

import kr.ac.hairou.model.Bookmark;

public interface BookmarkDao {

	Bookmark getItem(String member, int novel);

	void add(Bookmark item);

	int getTotal(int novel);

	void delete(Bookmark item);

}

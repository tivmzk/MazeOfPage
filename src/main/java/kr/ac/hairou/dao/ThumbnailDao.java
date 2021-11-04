package kr.ac.hairou.dao;

import kr.ac.hairou.model.Thumbnail;

public interface ThumbnailDao {

	void add(Thumbnail thumbnail);

	void delete(int code);

	Thumbnail getItem(int code);

}

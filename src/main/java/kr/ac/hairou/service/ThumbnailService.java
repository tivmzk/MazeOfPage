package kr.ac.hairou.service;

import kr.ac.hairou.model.Thumbnail;

public interface ThumbnailService {

	Thumbnail getItem(int code);

	void delete(int code);

}

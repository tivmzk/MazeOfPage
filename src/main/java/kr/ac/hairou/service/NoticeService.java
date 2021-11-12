package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.Pager;

public interface NoticeService {

	List<Notice> getList(Pager pager);

	void add(Notice item);

	Notice getItem(int code);

	void delete(int code);

	void update(Notice item);

}

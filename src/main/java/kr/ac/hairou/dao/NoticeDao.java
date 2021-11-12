package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.Pager;

public interface NoticeDao {

	List<Notice> getList(Pager pager);

	int getTotal(Pager pager);

	void add(Notice item);

}

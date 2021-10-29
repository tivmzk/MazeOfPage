package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.SearchOption;

public interface NoticeDao {

	List<Notice> getList(SearchOption option);

}

package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.SearchOption;

public interface NoticeService {

	List<Notice> getList(SearchOption option);

}

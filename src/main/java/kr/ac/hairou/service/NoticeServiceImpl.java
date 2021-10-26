package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.NoticeDao;
import kr.ac.hairou.model.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDao dao;
	
	@Override
	public List<Notice> getList() {
		return dao.getList();
	}

}

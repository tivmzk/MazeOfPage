package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.NoticeDao;
import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.Pager;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDao dao;

	@Override
	public List<Notice> getList(Pager pager) {
		int total = dao.getTotal(pager);
		pager.setTotal(total);
		return dao.getList(pager);
	}

	@Override
	public void add(Notice item) {
		dao.add(item);
	}

	@Override
	public Notice getItem(int code) {
		Notice item =  dao.getItem(code);
		String contents = item.getContents().replace("\n", "<br>");
		item.setContents(contents);
		return item;
	}

	@Override
	public void delete(int code) {
		dao.delete(code);
	}
	
	@Override
	public void update(Notice item) {
		dao.update(item);
	}
}

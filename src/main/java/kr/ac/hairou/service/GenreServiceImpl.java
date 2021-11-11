package kr.ac.hairou.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hairou.dao.GenreDao;
import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.Pager;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	GenreDao dao;
	
	@Override
	public List<Genre> getList(Pager pager) {
		return dao.getList(pager);
	}

	@Override
	public int getTotal() {
		return dao.getToal();
	}
	
	@Transactional
	@Override
	public Genre add(Genre item) {
		dao.add(item);
		return dao.getItem(item.getCode());
	}

	@Override
	public Map<String, Object> delete(int code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		try {
			dao.delete(code);
			map.put("msg", "성공적으로 삭제했습니다.");
			map.put("result", true);
		}
		catch (Exception e) {
			map.put("msg", "삭제에 실패했습니다.");
			map.put("result", false);
		}
		return map;
	}

}

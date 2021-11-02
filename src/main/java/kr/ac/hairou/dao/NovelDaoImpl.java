package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.Pager;

@Repository
public class NovelDaoImpl implements NovelDao {
	@Autowired
	SqlSession sql;

	@Override
	public void add(Novel item) {
		sql.insert("novel.add", item);
	}

	@Override
	public List<Novel> getList(Pager pager) {
		return sql.selectList("novel.list", pager);
	}

	@Override
	public int getTotal(Pager pager) {
		return sql.selectOne("novel.total", pager);
	}

	@Override
	public Novel getItem(int code) {
		return sql.selectOne("novel.item", code);
	}

}

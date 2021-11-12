package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.Pager;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public List<Notice> getList(Pager pager) {
		return sql.selectList("notice.list", pager);
	}

	@Override
	public int getTotal(Pager pager) {
		return sql.selectOne("notice.total");
	}

	@Override
	public void add(Notice item) {
		sql.insert("notice.add", item);
	}
	
	@Override
	public Notice getItem(int code) {
		return sql.selectOne("notice.item", code);
	}
	
	@Override
	public void delete(int code) {
		sql.delete("notice.delete", code);
	}
	
	@Override
	public void update(Notice item) {
		sql.update("notice.update", item);
	}
}

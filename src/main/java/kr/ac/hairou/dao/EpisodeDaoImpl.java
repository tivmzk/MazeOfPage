package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Episode;
import kr.ac.hairou.util.Pager;

/*episode를 DB에서 꺼내거나 넣는 DAO 클래스
전체적으로 SqlSession에게 일을 시키는 일을 한다*/
@Repository
public class EpisodeDaoImpl implements EpisodeDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public int getTotal(int code) {
		return sql.selectOne("episode.total", code);
	}

	@Override
	public List<Episode> getList(Pager pager) {
		return sql.selectList("episode.list", pager);
	}
	
	@Override
	public List<Episode> getListNoOption(Pager pager) {
		return sql.selectList("episode.list_no_option", pager);
	}

	@Override
	public Episode getItem(Pager pager) {
		return sql.selectOne("episode.item", pager);
	}

	@Override
	public void add(Episode item) {
		sql.insert("episode.add", item);
	}

	@Override
	public void delete(int code) {
		sql.delete("episode.delete", code);
	}

	@Override
	public void update(Episode item) {
		sql.update("episode.update", item);
	}

}

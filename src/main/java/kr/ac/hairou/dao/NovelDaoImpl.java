package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Novel;
import kr.ac.hairou.util.SearchOption;

@Repository
public class NovelDaoImpl implements NovelDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public List<Novel> getRanking(SearchOption searchOption) {
		return sql.selectList("novel.ranking", searchOption);
	}

	@Override
	public void add(Novel item) {
		sql.insert("novel.add", item);
	}

}

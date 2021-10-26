package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Novel;

@Repository
public class NovelDaoImpl implements NovelDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public List<Novel> getRanking() {
		return sql.selectList("novel.ranking");
	}

}

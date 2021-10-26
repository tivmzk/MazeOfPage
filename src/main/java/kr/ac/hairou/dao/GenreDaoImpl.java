package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Genre;

@Repository
public class GenreDaoImpl implements GenreDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public List<Genre> getList() {
		return sql.selectList("genre.list");
	}

}

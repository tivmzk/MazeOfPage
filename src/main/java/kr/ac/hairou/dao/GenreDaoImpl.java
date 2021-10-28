package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.util.SearchOption;

@Repository
public class GenreDaoImpl implements GenreDao {
	@Autowired
	SqlSession sql;

	@Override
	public List<Genre> getList(SearchOption option) {
		return sql.selectList("genre.list", option);
	}

}

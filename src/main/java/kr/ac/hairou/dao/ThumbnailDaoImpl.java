package kr.ac.hairou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Thumbnail;

@Repository
public class ThumbnailDaoImpl implements ThumbnailDao{
	@Autowired
	SqlSession sql;
	
	@Override
	public void add(Thumbnail thumbnail) {
		sql.insert("thumbnail.add", thumbnail);
	}

	@Override
	public void delete(int code) {
		sql.delete("thumbnail.delete", code);
	}

	@Override
	public Thumbnail getItem(int code) {
		return sql.selectOne("thumbnail.item", code);
	}

}

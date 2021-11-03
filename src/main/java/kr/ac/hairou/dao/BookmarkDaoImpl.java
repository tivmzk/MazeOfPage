package kr.ac.hairou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Bookmark;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public Bookmark getItem(String member, int novel) {
		Bookmark bookmark = new Bookmark();
		bookmark.setMember(member);
		bookmark.setNovel(novel);
		return sql.selectOne("bookmark.item", bookmark);
	}

	@Override
	public void add(Bookmark item) {
		sql.insert("bookmark.add", item);
	}

	@Override
	public int getTotal(int novel) {
		return sql.selectOne("bookmark.total", novel);
	}

	@Override
	public void delete(Bookmark item) {
		sql.delete("bookmark.delete", item);
	}
}

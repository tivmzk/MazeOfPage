package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Comments;
import kr.ac.hairou.util.Pager;

@Repository
public class CommentsDaoImpl implements CommentsDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public void add(Comments item) {
		sql.insert("comments.add", item);
	}

	@Override
	public List<Comments> getList(Pager pager) {
		return sql.selectList("comments.list", pager);
	}
	
	@Override
	public Comments getItem(int code) {
		return sql.selectOne("comments.item", code);
	}
	
	@Override
	public void delete(int code) {
		sql.delete("comments.delete", code);
	}
	
	@Override
	public void update(Comments item) {
		sql.update("comments.update", item);
	}
	
	@Override
	public int total(int code) {
		return sql.selectOne("comments.total", code);
	}
}

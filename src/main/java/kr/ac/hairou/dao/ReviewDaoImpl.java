package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Review;
import kr.ac.hairou.util.Pager;

@Repository
public class ReviewDaoImpl implements ReviewDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public List<Review> getList(Pager pager) {
		return sql.selectList("review.list", pager);
	}

	@Override
	public void add(Review item) {
		sql.insert("review.add", item);
	}

	@Override
	public Review getItem(int code) {
		return sql.selectOne("review.item", code);
	}

	@Override
	public void update(Review item) {
		sql.update("review.update", item);
	}

	@Override
	public void delete(int code) {
		sql.delete("review.delete", code);
	}

	@Override
	public int getTotal(Pager pager) {
		return sql.selectOne("review.total", pager);
	}

}

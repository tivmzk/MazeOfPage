package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Review;
import kr.ac.hairou.util.Pager;

public interface ReviewDao {

	List<Review> getList(Pager pager);

	void add(Review item);

	Review getItem(int code);

	void update(Review item);

	void delete(int code);

	int getTotal(Pager pager);

}

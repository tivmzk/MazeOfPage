package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Review;
import kr.ac.hairou.util.Pager;

public interface ReviewService {

	List<Review> getList(Pager pager);

	void add(Review item);

	Review getItem(int code);

	void update(Review item);

	void delete(int code);
	
}

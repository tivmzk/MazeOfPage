package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Comments;
import kr.ac.hairou.util.Pager;

public interface CommentsService {

	Comments add(Comments item);

	List<Comments> getList(Pager pager);

	void delete(int code);

	void update(Comments item);

	int total(int code);

}

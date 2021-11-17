package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Comments;
import kr.ac.hairou.util.Pager;

public interface CommentsDao {

	void add(Comments item);

	List<Comments> getList(Pager pager);

	Comments getItem(int code);

	void delete(int code);

	void update(Comments item);

}

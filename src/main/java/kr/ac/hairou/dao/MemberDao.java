package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Member;
import kr.ac.hairou.util.Pager;

public interface MemberDao {

	void add(Member item);

	Member getItem(Member item);

	List<Member> getList(Pager pager);

}

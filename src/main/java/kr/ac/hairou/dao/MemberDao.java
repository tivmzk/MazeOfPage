package kr.ac.hairou.dao;

import java.util.List;

import kr.ac.hairou.model.Member;
import kr.ac.hairou.util.SearchOption;

public interface MemberDao {

	void add(Member item);

	Member getItem(String id);

	List<Member> getList(SearchOption searchOption);

}

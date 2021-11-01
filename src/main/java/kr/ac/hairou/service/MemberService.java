package kr.ac.hairou.service;

import java.util.List;

import kr.ac.hairou.model.Member;
import kr.ac.hairou.util.Pager;

public interface MemberService {

	void add(Member item);

	Member getItem(Member member);

	List<Member> getList(Pager pager);

}

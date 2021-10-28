package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.MemberDao;
import kr.ac.hairou.model.Member;
import kr.ac.hairou.util.SearchOption;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;
	
	@Override
	public void add(Member item) {
		dao.add(item);
	}

	@Override
	public Member getItem(String id) {
		return dao.getItem(id);
	}

	@Override
	public List<Member> getList(SearchOption searchOption) {
		return dao.getList(searchOption);
	}

}

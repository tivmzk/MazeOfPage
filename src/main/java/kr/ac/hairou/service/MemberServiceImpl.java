package kr.ac.hairou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.MemberDao;
import kr.ac.hairou.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;
	
	@Override
	public void add(Member item) {
		dao.add(item);
	}

}

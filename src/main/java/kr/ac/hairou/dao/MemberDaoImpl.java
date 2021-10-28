package kr.ac.hairou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public void add(Member item) {
		sql.insert("member.add", item);
	}

}

package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Member;
import kr.ac.hairou.util.Pager;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public void add(Member item) {
		sql.insert("member.add", item);
	}

	@Override
	public Member getItem(Member item) {
		return sql.selectOne("member.item", item);
	}

	@Override
	public List<Member> getList(Pager pager) {
		return sql.selectList("member.list", pager);
	}

}

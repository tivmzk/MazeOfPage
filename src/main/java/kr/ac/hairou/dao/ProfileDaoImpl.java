package kr.ac.hairou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Profile;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public Profile getItem(String member) {
		return sql.selectOne("profile.item", member);
	}

}

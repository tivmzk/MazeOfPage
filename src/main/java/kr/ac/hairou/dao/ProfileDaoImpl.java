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

	@Override
	public boolean exist(Profile item) {
		int cnt = sql.selectOne("profile.count", item);
		if(cnt == 0)
			return false;
		else
			return true;
	}

	@Override
	public void update(Profile item) {
		sql.update("profile.update", item);
	}

	@Override
	public void add(Profile item) {
		sql.insert("profile.add", item);
	}

	@Override
	public void updateNickname(Profile item) {
		sql.update("profile.updateNickname", item);
	}

}

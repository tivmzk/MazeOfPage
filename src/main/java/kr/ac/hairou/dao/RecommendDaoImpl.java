package kr.ac.hairou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Recommend;

@Repository
public class RecommendDaoImpl implements RecommendDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public Recommend getItem(String member, int novel) {
		Recommend recom = new Recommend();
		recom.setMember(member);
		recom.setNovel(novel);
		return sql.selectOne("recommend.item", recom);
	}

	@Override
	public void add(Recommend item) {
		sql.insert("recommend.add", item);
	}

	@Override
	public int getTotal(int novel) {
		return sql.selectOne("recommend.total", novel);
	}

	@Override
	public void delete(Recommend item) {
		sql.delete("recommend.delete", item);
	}
}

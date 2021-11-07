package kr.ac.hairou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Option;

@Repository
public class OptionDaoImpl implements OptionDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public void add(Option option) {
		sql.insert("option.add", option);
	}
	
	@Override
	public void delete(int code) {
		sql.delete("option.delete", code);
	}
}

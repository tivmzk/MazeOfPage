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
//	episode의 기본키를 mepisode로 가지고 있는 option들을 전부 삭제
	@Override
	public void deleteAll(int code) {
		sql.delete("option.delete", code);
	}
//	입력한 episode의 기본키를 oepisode로 가지고 있는 option의 값을 null로 변경 
	@Override
	public void deleteDependency(int code) {
		sql.update("option.deleteDependency", code);
	}
//	입력한 option.code로 한개 삭제
	@Override
	public void deleteOne(int code) {
		sql.delete("option.deleteOne", code);
	}
}

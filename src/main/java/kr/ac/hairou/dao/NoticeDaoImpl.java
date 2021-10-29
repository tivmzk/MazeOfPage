package kr.ac.hairou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.hairou.model.Notice;
import kr.ac.hairou.util.SearchOption;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	@Autowired
	SqlSession sql;
	
	@Override
	public List<Notice> getList(SearchOption option) {
		return sql.selectList("notice.list", option);
	}

}

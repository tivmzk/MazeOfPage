package kr.ac.hairou.dao;

import kr.ac.hairou.model.Option;

public interface OptionDao {

	void add(Option option);

	void delete(int code);

}

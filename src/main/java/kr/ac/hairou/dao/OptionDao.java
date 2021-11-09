package kr.ac.hairou.dao;

import kr.ac.hairou.model.Option;

public interface OptionDao {

	void add(Option option);

	void deleteAll(int code);

	void deleteDependency(int code);

	void deleteOne(int code);

}

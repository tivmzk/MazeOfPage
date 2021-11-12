package kr.ac.hairou.dao;

import kr.ac.hairou.model.Profile;

public interface ProfileDao {

	Profile getItem(String member);

	boolean exist(Profile item);

	void update(Profile item);

	void add(Profile item);

	void updateNickname(Profile item);

}

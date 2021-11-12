package kr.ac.hairou.service;

import kr.ac.hairou.model.Profile;

public interface ProfileService {

	Profile getItem(String member);

	void update(Profile item);

}

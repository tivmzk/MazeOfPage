package kr.ac.hairou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hairou.dao.ProfileDao;
import kr.ac.hairou.model.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileDao dao;
	
	@Override
	public Profile getItem(String member) {
		return dao.getItem(member);
	}

	@Override
	public void update(Profile item) {
		if(dao.exist(item)) {
//			프로필이 존재할 경우
			dao.update(item);
			dao.updateNickname(item);
		}
		else {
//			프로필이 없을 경우
			dao.add(item);
			dao.update(item);
			dao.updateNickname(item);
		}
	}

}

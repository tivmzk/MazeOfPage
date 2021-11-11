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

}

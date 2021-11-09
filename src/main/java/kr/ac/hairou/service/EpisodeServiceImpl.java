package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hairou.dao.EpisodeDao;
import kr.ac.hairou.dao.OptionDao;
import kr.ac.hairou.model.Episode;
import kr.ac.hairou.model.Option;
import kr.ac.hairou.util.Pager;

@Service
public class EpisodeServiceImpl implements EpisodeService {
	@Autowired
	EpisodeDao dao;
	@Autowired
	OptionDao optionDao;
	
	@Override
	public int getTotal(int code) {
		return dao.getTotal(code);
	}

	@Override
	public List<Episode> getList(Pager pager) {
		int total = dao.getTotal(Integer.parseInt(pager.getKeyword()));
		pager.setTotal(total);
		return dao.getList(pager);
	}

	@Override
	public Episode getItem(Pager pager) {
		return dao.getItem(pager);
	}
	
	@Transactional
	@Override
	public void add(Episode item) {
		dao.add(item);
		
		if(item.getOptions().size() > 0) {
			for(Option option : item.getOptions()) {
				if(option.getOepisode() == -2) continue;
				
				option.setMepisode(item.getCode());
				
				if(option.getOepisode() == -1) {
					Episode newEpi = new Episode();
					
					newEpi.setNovel(item.getNovel());
					newEpi.setTitle(option.getAction());
					newEpi.setContents("");
					newEpi.setIsStart('0');
					
					dao.add(newEpi);
					
					option.setOepisode(newEpi.getCode());
				}
				
				optionDao.add(option);
			}
		}
	}
	
	@Transactional
	@Override
	public void delete(int code) {
		optionDao.deleteDependency(code);
		optionDao.delete(code);
		dao.delete(code);
	}

	@Override
	public void update(Episode item) {
		dao.update(item);
	}

}

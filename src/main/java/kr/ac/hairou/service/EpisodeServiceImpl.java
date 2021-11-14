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

/*EpisodeController가 시킨일을 처리하는 Service 클래스
 * Dao에게 일을 시키고 여러 비지니스 로직을 처리한다
*/
@Service
public class EpisodeServiceImpl implements EpisodeService {
	@Autowired
	EpisodeDao dao;
	@Autowired
	OptionDao optionDao;
	
//	episode의 총 개수를 반환하는 메서드
	@Override
	public int getTotal(int code) {
		return dao.getTotal(code);
	}

	/*
	 * episode 리스트를 반환하는 메서드
	 * episode가 가지고 있는 option들을 조립한다
	 * ORM(resultMap)을 사용하지 않은 이유는 
	 * LIMIT를 이용해 Pagnation를 할 때 10개를 가지고 오고 싶은데
	 * LIMIT로 10개 제한 후 resultMap이 OUTER JOIN으로 중복이 되는 값을
	 * 합쳐주기 때문에 원하는 개수의 값을 얻을 수 없기 때문이다
	 * 그래서 OptionDao로 원하는 option을 불러와서 여기서 조립한다
	 */
	@Override
	public List<Episode> getList(Pager pager) {
		int total = dao.getTotal(Integer.parseInt(pager.getKeyword()));
		pager.setTotal(total);
		List<Episode> list = dao.getListNoOption(pager);
		
		for(Episode item : list) {
			List<Option> options = optionDao.list(item.getCode());
			item.setOptions(options);
		}
		
		return list;
	}
//	episode 한개를 반환한다
	@Override
	public Episode getItem(Pager pager) {
		return dao.getItem(pager);
	}
	
	/*
	 * episode를 추가하는 메서드
	 * episode안에 option이 들어있어서 같이 추가해 준다
	 * 트랜젝션을 사용해서 중간에 에러가 나면 
	 * 원자성으로 인해 롤백이 된다
	 * option의 oepisode는 그 option을 선택하면 들어가지는 episode인데
	 * 그 값이 -2면 이어진 episode가 없는거기 때문에 생략하고
	 * -1이면 없는 episode와 이으려는 거기 때문에 episode를 새로 생성한다
	 */
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
	
	/*
	 * episode를 삭제하는 메서드
	 * 이 episode를 왜래키로 가지고 있는 option들의 처리를 같이한다
	 * oepisode로 가지고 있으면 null로 만들고
	 * mepisode(option을 가지고 있는 episode)로 가지고 있는 option은 삭제한다
	 */
	@Transactional
	@Override
	public void delete(int code) {
		optionDao.deleteDependency(code);
		optionDao.deleteAll(code);
		dao.delete(code);
	}
	
	/*
	 * episode를 수정하는 메서드
	 * episode와 연관된 option은 전부 지우고 
	 * 입력된 option으로 새로 만들어서 저장한다
	 * 트랜젝션을 사용한다
	 */
	@Transactional
	@Override
	public void update(Episode item) {
		dao.update(item);
		
		if(item.getOptions().size() > 0) {
			optionDao.deleteAll(item.getCode());
			
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

}

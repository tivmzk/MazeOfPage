package kr.ac.hairou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hairou.dao.NovelDao;
import kr.ac.hairou.dao.ThumbnailDao;
import kr.ac.hairou.model.Episode;
import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.Member;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.model.Thumbnail;
import kr.ac.hairou.util.FileManager;
import kr.ac.hairou.util.Pager;
import kr.ac.hairou.util.PreviewManager;

@Service
public class NovelServiceImpl implements NovelService {
	@Autowired
	NovelDao dao;
	@Autowired
	ThumbnailDao tdao;
	@Autowired
	GenreService genreService;
	@Autowired
	EpisodeService epiService;
	
	@Transactional
	@Override
	public void dummy(Member member) {
		Pager pager = new Pager();
		int total = genreService.getTotal();
		pager.setTotal(total);
		List<Genre> list = genreService.getList(pager);
		FileManager manager = new PreviewManager();
		
		for(Genre genre : list) {
			for(int i = 0; i < 10; i++) {
				Novel item = new Novel();
				item.setMember(member.getId());
				item.setGenre(genre.getCode());
				item.setTitle(String.format("장르 %d 소설", genre.getCode()));
				item.setInfo(String.format("장르 %d 소설의 내용", genre.getCode()));
				item.setRecom((int)(Math.random() * 100));
				Thumbnail thumbnail = null;
				
				try {
					thumbnail = manager.upload();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				item.setImage(thumbnail);
				
				add(item);
			}
		}
		
		
		
	}

	@Override
	public List<Novel> getList(Pager pager) {
		int total = dao.getTotal(pager);
		pager.setTotal(total);
		return dao.getList(pager);
	}
	
	@Transactional
	@Override
	public void add(Novel item) {
		dao.add(item);
		item.getImage().setNovel(item.getCode());
		tdao.add(item.getImage());
	}

	@Override
	public Novel getItem(int code) {
		Novel item = dao.getItem(code);
		String info = item.getInfo();
		item.setInfo(info.replaceAll("\n", "<br>"));
		return item;
	}
	
	@Transactional
	@Override
	public void delete(int code) {
		Pager pager = new Pager();
		pager.setSearch(1);
		pager.setKeyword(""+code);
		List<Episode> list = epiService.getList(pager);
		
		for(Episode item : list) {
			epiService.delete(item.getCode());
		}
		
		tdao.delete(code);
		dao.delete(code);
	}
	
	@Transactional
	@Override
	public void update(Novel item) {
		dao.update(item);
		if(item.getImage() != null) {
			item.getImage().setNovel(item.getCode());
			tdao.add(item.getImage());	
		}
	}

}

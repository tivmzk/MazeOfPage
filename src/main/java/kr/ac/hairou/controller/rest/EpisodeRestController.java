package kr.ac.hairou.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hairou.model.Episode;
import kr.ac.hairou.service.EpisodeService;
import kr.ac.hairou.util.Pager;

/*Episode와 관련된 ajax를 처리하는 Controller
RestController를 사용하여 @ResponseBody를 사용하지 않아도 된다*/
@RequestMapping("/rest/episode")
@RestController
public class EpisodeRestController {
	
	@Autowired
	EpisodeService episodeService;
	
	/*
	 * 특정 작품의 에피소드를 전부 반환하는 메서드 에피소드 작성시 
	 * 선택지를 추가할 때 연결되는 에피소드를 지정하는데 사용된다
	 */
	@GetMapping("/all")
	public List<Episode> listAll(Pager pager){
		int total = episodeService.getTotal(Integer.parseInt(pager.getKeyword()));
		pager.setPerPage(total);
		return episodeService.getList(pager);
	}
	
	/*
	 * 작품 상세 페이지에서 사용하는 메서드 상세 페이지에서 
	 * 에피소드는 ajax로 처리해서 여기에 있다 
	 * pager에 기본값에 따라 10개 반환한다
	 */
	@GetMapping
	public Map<String, Object> list(Pager pager){
		Map<String, Object> map = new HashMap<>();
		List<Episode> list = episodeService.getList(pager);
		map.put("episodes", list);
		map.put("pager", pager);
		return map;
	}
	
	/*
	 * 에피소드를 한개 반환하는 메서드
	 * 에피소드를 수정할 때 사용한다
	 */
	@GetMapping("/item")
	public Episode item(Pager pager) {
		Episode item = episodeService.getItem(pager);
		return item;
	}

	/*
	 * 에피소드를 삭제할 때 사용
	*/
	@DeleteMapping
	public int delete(int code) {
		episodeService.delete(code);
		return code;
	}
}

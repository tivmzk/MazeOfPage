package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hairou.model.Episode;
import kr.ac.hairou.model.Option;
import kr.ac.hairou.service.EpisodeService;
import kr.ac.hairou.util.Pager;

@RequestMapping("/episode")
@Controller
public class EpisodeController {
	private final String PATH = "episode/";
	
	@Autowired
	EpisodeService epiService;
	
	@GetMapping("/add")
	public String add(int code, Model model, Pager pager) {
		pager.setKeyword(""+code);
		pager.setSearch(1);
		List<Episode> list = epiService.getList(pager);
		model.addAttribute("code", code);
		model.addAttribute("episodeList", list);
		pager.reset();
		return PATH+"add.main";
	}
	
	@PostMapping("/add")
	public String add(Episode item, @RequestParam("oepisode") List<Integer> oepisodes, @RequestParam("action") List<String> actions) {
		for(int i = 0; i < oepisodes.size(); i++) {
			Option option = new Option();
			option.setOepisode(oepisodes.get(i));
			option.setAction(actions.get(i));
			item.getOptions().add(option);
		}
		
		epiService.add(item);
		return "redirect:/novel/detail/"+item.getNovel();
	}
}

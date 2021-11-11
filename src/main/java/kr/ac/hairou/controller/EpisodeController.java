package kr.ac.hairou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/add/{code}")
	public String add(@PathVariable int code, Model model, Pager pager) {
		pager.setKeyword(""+code);
		pager.setSearch(1);
		List<Episode> list = epiService.getList(pager);
		model.addAttribute("code", code);
		model.addAttribute("episodeList", list);
		pager.reset();
		return PATH+"add.main";
	}
	
	@PostMapping("/add/{code}")
	public String add(@PathVariable int code, Episode item, @RequestParam("oepisode") List<Integer> oepisodes, @RequestParam("action") List<String> actions) {
		item.setNovel(code);
		if(oepisodes != null) {
			for(int i = 0; i < oepisodes.size(); i++) {
				Option option = new Option();
				option.setOepisode(oepisodes.get(i));
				try {
					option.setAction(actions.get(i));
				}
				catch (Exception e) {
					option.setAction("");
				}
				
				item.getOptions().add(option);
			}
		}
		
		epiService.add(item);
		return "redirect:/novel/detail/"+item.getNovel();
	}
	

	@GetMapping("/update/{code}/{epi}")
	public String update(@PathVariable int code, @PathVariable int epi, Model model, Pager pager) {
		pager.setKeyword(""+code);
		pager.setSearch(1);
		
		List<Episode> list = epiService.getList(pager);
		
		pager.setKeyword(""+epi);
		pager.setSearch(4);
		
		Episode item = epiService.getItem(pager);
		
		model.addAttribute("item", item);
		model.addAttribute("code", code);
		model.addAttribute("episodeList", list);
		pager.reset();
		return PATH+"update.main";
	}
	
	@PostMapping("/update/{code}/{epi}")
	public String update(@PathVariable int code, @PathVariable int epi, Episode item, 
						@RequestParam("oepisode") List<Integer> oepisodes, @RequestParam("action") List<String> actions,
						@RequestParam("code") List<Integer> codes) {
		item.setCode(epi);
		item.setNovel(code);
		if(oepisodes != null) {
			for(int i = 0; i < oepisodes.size(); i++) {
				Option option = new Option();
				if(i < codes.size())
					option.setCode(codes.get(i));
				
				option.setOepisode(oepisodes.get(i));
				try {
					option.setAction(actions.get(i));
				}
				catch (Exception e) {
					option.setAction("");
				}
				
				item.getOptions().add(option);
			}
		}
		epiService.update(item);
		return "redirect:/novel/detail/"+item.getNovel();
	}
	
	@GetMapping("/{novel}/{epi}")
	public String view(@PathVariable int novel, @PathVariable int epi, Model model, Pager pager) {
		pager.setSearch(4);
		pager.setKeyword(epi+"");
		Episode item = epiService.getItem(pager);
		model.addAttribute("item", item);
		model.addAttribute("novel", novel);
		pager.reset();
		return PATH+"view.main";
	}
}

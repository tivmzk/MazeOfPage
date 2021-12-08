package kr.ac.hairou.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ac.hairou.model.Genre;
import kr.ac.hairou.model.GenreRank;
import kr.ac.hairou.model.Member;
import kr.ac.hairou.model.Notice;
import kr.ac.hairou.model.Novel;
import kr.ac.hairou.model.Review;
import kr.ac.hairou.service.GenreService;
import kr.ac.hairou.service.MemberService;
import kr.ac.hairou.service.NoticeService;
import kr.ac.hairou.service.NovelService;
import kr.ac.hairou.service.ReviewService;
import kr.ac.hairou.util.Pager;

/*
 * 메인 페이지, 로그인, 회원가입을 담당하는 컨트롤러입니다
 * 
 * 테스트를 위한 dummy나 회원가입을 위한 ajax를 받기도 합니다
 * 
 * */
@Controller
public class RootController {
	@Autowired
	NovelService novelService;
	@Autowired
	GenreService genreService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	MemberService memberService;
	@Autowired
	ReviewService reviewService;
	/*
	 * 메인 페이지에 오는 사용자를 위한 메소드
	 * 메인 페이지에 배치된 컨텐츠를 채우기 위해
	 * NovelService, GenreService, ReviewService, NoticeService를 사용
	 * 받아오는 리스트의 수를 제한하기 위해 pager를 사용한다
	 * pager를 사용하면 검색창에 keyword가 남기 때문에 pager를 초기화 해준다
	 * */
	@RequestMapping("/")
	public String index(Model model, Pager pager) {
		try {
			pager.setPerPage(5);
			pager.setOrder(1);
			List<Novel> rankingList = novelService.getList(pager);
			pager.setPerPage(2);
			pager.setOrder(0);
			List<Novel> novelList = novelService.getList(pager);
			pager.setPerPage(3);
			List<Genre> genreList = genreService.getList(pager);
			pager.setPerPage(5);
			List<Notice> noticeList = noticeService.getList(pager);
			pager.setPerPage(5);
			List<Review> reviewList = reviewService.getList(pager); 
			List<GenreRank> genreRankList = new ArrayList<>();
			
			for(Genre genre : genreList) {
				pager.setPerPage(5);
				pager.setOrder(1);
				pager.setKeyword(genre.getContents());
				pager.setSearch(3);
				List<Novel> list = novelService.getList(pager);
				genreRankList.add(new GenreRank(genre.getContents(), list));
			}
			
			model.addAttribute("rankingList", rankingList);
			model.addAttribute("novelList", novelList);
			model.addAttribute("genreList", genreList);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("genreRankList", genreRankList);
			pager.reset();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index.main";
	}
	
//	테스트를 위한 더미를 생성하는 메소드
	@GetMapping("/dummy")
	public String dummy(HttpSession session){
		Member member = (Member) session.getAttribute("user");
		novelService.dummy(member);
		return "redirect:.";
	}
	
//	로그인 페이지로 보내는 메소드
	@GetMapping("/login")
	public String login() {
		return "login.main";
	}
	
	/*
	 * 로그인을 할 지 안할지 판단하는 메서드 로그인에 실패하면 RedirectAttributes로 jsp에 메세지를 보낸다
	 * FlashAttribute에 넣은 값은 한번 사용하면 사라지는 휘발성이다
	 */
	@PostMapping("/login")
	public String login(Member member, HttpSession session, RedirectAttributes attr) {
		Member item = memberService.getItem(member);
		
		if(item == null) {
			attr.addFlashAttribute("warn", "아이디 또는 비밀번호가 틀렸습니다");
			return "redirect:login";
		}
		
		session.setAttribute("user", item);
		
		return "redirect:/";
	}
	
//	로그아웃을 담당하는 메서드
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:.";
	}
	
//	회원가입 페이지로 보내주는 메서드
	@GetMapping("/signup")
	public String signup(){
		return "signup.main";
	}
	
//	회원가입을 처리하는 메서드
	@PostMapping("/signup")
	public String signup(Member item){
		memberService.add(item);
		return "redirect:login";
	}
	
	/*
	 * 회원가입시 닉네임이 중복인가 아닌가를 ajax한 것을 처리하는 메서드
	 * 
	 * @ResponseBody를 사용해서 html이 아닌 값 자체를 보낸 수 있고
	 * Jackson 라이브러리를 사용해서 문자열이 아니여도 반환할 수 있다
	 */
	@ResponseBody
	@GetMapping("/check_nickname/{nickname}")
	public boolean checkNickname(@PathVariable String nickname){
		Pager pager = new Pager();
		pager.setKeyword(nickname);
		pager.setSearch(1);
		List<Member> list = memberService.getList(pager);
		
		if(list.size() < 1)
			return true;
		else
			return false;
	}
	
//	회원가입시 아이디가 중복인가 아닌가를 ajax한 것을 처리하는 메서드
	@ResponseBody
	@GetMapping("/check_id/{id}")
	public boolean checkId(@PathVariable String id){
		Pager pager = new Pager();
		pager.setKeyword(id);
		pager.setSearch(2);
		List<Member> list = memberService.getList(pager);
		
		if(list.size() < 1)
			return true;
		else
			return false;
	}
}

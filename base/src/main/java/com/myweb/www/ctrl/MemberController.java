package com.myweb.www.ctrl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProfileVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.handler.ProfileHandler;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
	// 스프링 프레임워크는 실행할 때 @Component로 등록된 모든 클래스를 사전에 인스턴스로 만든다
	// @Component로 인스턴스된 클래스들은 @Injext, @Autowired를 통해 사용할 수 있다
	// DispatcherServlet은 request가 발생하면 분석하기 위해 @requestMapping을 호출한다
	// @requestMapping은 uri값의 패턴에 따라 각 메서드에게 실행주문을 전달한다

	@Inject
	private MemberService msv;
	
	private final BCryptPasswordEncoder bcpEncoder;
	
	@Inject
	private ProfileHandler prhd;
//	private final MemberService msv;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {
		// request의 uri 패턴이 같다면 void로 선언 후 그대로 viewResolver에게 전달. 해당 uri와 같은 위치의 jsp로
		// forword된다
		log.info(">>> MemberController > register > register - GET");
	}

//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String register(MemberVO mvo) {
//		log.info(">>> register-mvo : {}", mvo);
//		int isUp = msv.register(mvo);
//		log.info(">>> member register - post : {}", isUp > 0 ? "ok" : "fail");
//		return "redirect:/";
//	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MemberVO mvo, @RequestParam(name = "profile", required = false) MultipartFile file) {
		log.info(">>> register-mvo : {}", mvo);
		mvo.setPwd(bcpEncoder.encode(mvo.getPwd()));
		ProfileVO prvo = null;

		if (file != null) {
			prvo = prhd.getProfile(file);
		}

		int isUp = msv.register(new MemberDTO(mvo, prvo));
		log.info(">>> member register - post : {}", isUp > 0 ? "ok" : "fail");
		return "redirect:/";
	}

	@GetMapping("/list") // Springframework 4.3.부터 지원
	public void list(Model model, PagingVO pgvo) {
		log.info(">>> member list - get");
		List<MemberVO> list = msv.getList(pgvo);
		model.addAttribute("list", list);
		int totalCount = msv.getTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}

//	@GetMapping({"/detail", "/modify"})
//	public void detail(Model model, @RequestParam("email") String email, @ModelAttribute("pgvo") PagingVO pgvo) {
//		log.info(">>> member detail - get");
//		MemberVO mvo = msv.getDetail(email);
//		model.addAttribute("mvo", mvo);
//	}

	@GetMapping({ "/detail", "/modify" })
	public void detail(Model model, @RequestParam("email") String email, @ModelAttribute("pgvo") PagingVO pgvo) {
		log.info(">>> member detail - get");
		model.addAttribute("mdto", msv.getDetail(email));
	}

//	@GetMapping("/modify")
//	public void modify(Model model, @RequestParam("email") String email) {
//		log.info(">>> member modify - get");
//		MemberVO mvo = msv.getDetail(email);
//		model.addAttribute("mvo", mvo);
//	}

//	@PostMapping("/modify")
//	public String modify(MemberVO mvo, PagingVO pgvo, RedirectAttributes rttr) {
//		log.info(">>> member modify - post");
//		int isUp = msv.modify(mvo);
//		log.info(">>> member modify : {}", isUp > 0 ? "ok" : "fail");
//		rttr.addAttribute("pageNo", pgvo.getPageNo());
//		rttr.addAttribute("qty", pgvo.getQty());
//		return "redirect:/member/detail?email=" + mvo.getEmail();
//	}

	@PostMapping("/modify")
	public String modify(MemberVO mvo, PagingVO pgvo, RedirectAttributes rttr,
			@RequestParam(name = "profile", required = false) MultipartFile file) {
		log.info(">>> member modify - post");
		ProfileVO prvo = null;

		if (file != null) {
			prvo = prhd.getProfile(file);
		}

		int isUp = msv.modify(new MemberDTO(mvo, prvo));
		log.info(">>> member modify : {}", isUp > 0 ? "ok" : "fail");
		rttr.addAttribute("pageNo", pgvo.getPageNo());
		rttr.addAttribute("qty", pgvo.getQty());
		rttr.addAttribute("type", pgvo.getType());
		rttr.addAttribute("kw", pgvo.getKw());
		return "redirect:/member/detail?email=" + mvo.getEmail();
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("email") String email, PagingVO pgvo, RedirectAttributes rttr) {
		int isUp = msv.remove(email);
		log.info(">>> member remove : {}", isUp > 0 ? "ok" : "fail");
//		rttr.addAttribute("pageNo", pgvo.getPageNo());
//		rttr.addAttribute("qty", pgvo.getQty());
		return "redirect:/member/list";
	}

	@GetMapping("/login")
	public void login() {
		log.info(">>> member login - get");
	}

	@PostMapping("/login")
	public String login(HttpServletRequest req, RedirectAttributes rttr) {
//		log.info(">>> member login - post");
//		MemberVO sesMvo = msv.login(mvo);
//		if (sesMvo != null) {
//			ses.setAttribute("ses", sesMvo);
//			ses.setMaxInactiveInterval(10 * 60); // 10min
//			rttr.addFlashAttribute("isLogin", 1);
//			return "redirect:/";
//		} else {
////			rttr.addFlashAttribute("isLogin", 0); 경로가 홈이면 가는데 자기자신은 안가는듯....?
//			return "redirect:/member/login";
//		}
		rttr.addFlashAttribute("email", req.getAttribute("email"));
		rttr.addFlashAttribute("errMsg", req.getAttribute("errMsg"));
		return "redirect:/member/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession ses, RedirectAttributes rttr) {
		log.info(">>> member logout");
		ses.removeAttribute("ses"); // ses라는 이름을 갖는 세션 객체 삭제
		ses.invalidate();
		rttr.addFlashAttribute("isLogout", 1);
		return "redirect:/";
	}

	@ResponseBody
	@PostMapping(value = "/dupleCheck", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String dupleCheck(@RequestBody HashMap<String, String> map) {
		log.info(">>> {}", map.get("email"));
		int isExist = msv.dupleCheck(map.get("email"));
		return isExist > 0 ? "1" : "0";
	}
}

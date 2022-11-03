package com.myweb.www.ctrl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.ProfileVO;
import com.myweb.www.handler.ProfileHandler;
import com.myweb.www.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Inject
	private MemberService msv;

	@Inject
	private ProfileHandler prhd;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register() {}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MemberVO mvo, @RequestParam(name = "profile", required = false) MultipartFile file) {
		ProfileVO prvo = null;

		if (file != null) {
			prvo = prhd.getProfile(file);
		}

		msv.register(new MemberDTO(mvo, prvo));
		return "redirect:/";
	}

	@GetMapping("/login")
	public void login() {}

	@PostMapping("/login")
	public String login(MemberVO mvo, HttpSession ses, RedirectAttributes rttr) {
		MemberVO sesMvo = msv.login(mvo);
		if (sesMvo != null) {
			ses.setAttribute("ses", sesMvo);
			ses.setMaxInactiveInterval(10 * 60);
			rttr.addFlashAttribute("isLogin", 1);
			return "redirect:/";
		} else {
			return "redirect:/member/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession ses, RedirectAttributes rttr) {
		ses.removeAttribute("ses");
		ses.invalidate();
		rttr.addFlashAttribute("isLogout", 1);
		return "redirect:/";
	}
}

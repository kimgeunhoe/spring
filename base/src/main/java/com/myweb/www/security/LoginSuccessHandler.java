package com.myweb.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.myweb.www.service.MemberService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Getter
	@Setter
	private String authEmail;
	
	@Getter
	@Setter
	private String authUrl;
	
	private RequestCache reqCache = new HttpSessionRequestCache();
	private RedirectStrategy rdStg = new DefaultRedirectStrategy();
	
	@Inject
	private MemberService msv;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info(">>> login success");
		setAuthEmail(authentication.getName());
		setAuthUrl("/product/list");
		
		boolean isUp = msv.updateLastLogin(getAuthEmail());
		
		//새로 생성된 세션이 아닌 기존에 존재하는 세션을 가져오기
		HttpSession ses = request.getSession(false);
		
		if(!isUp || ses==null) {
			return;
		} else {
			//시큐리티에서 시도한 인증 실패 기록(캐싱된 데이터) 삭제
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		
		SavedRequest savedReq = reqCache.getRequest(request, response);
		rdStg.sendRedirect(request, response, (savedReq!=null ? savedReq.getRedirectUrl() : getAuthUrl()));
	}

}

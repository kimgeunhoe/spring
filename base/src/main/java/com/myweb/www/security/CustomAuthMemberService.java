package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.domain.MemberVO;
import com.myweb.www.repository.MemberDAO;

public class CustomAuthMemberService implements UserDetailsService {
	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//로그인 from에서 보낸 email값을 시큐리티에 의해서 username으로 받아옴
		MemberVO mvo = mdao.selectAuth(username);
		mvo.setAuthList(mdao.selectAuthList(username));
		return new AuthMember(mvo);
	}

}

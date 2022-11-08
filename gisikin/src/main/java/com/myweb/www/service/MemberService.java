package com.myweb.www.service;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;

public interface MemberService {
	int register(MemberDTO mdto);
	MemberVO login(MemberVO mvo);
}

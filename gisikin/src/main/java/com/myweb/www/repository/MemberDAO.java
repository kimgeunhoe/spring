package com.myweb.www.repository;

import com.myweb.www.domain.MemberVO;

public interface MemberDAO {
	int insert(MemberVO mvo);
	MemberVO login(MemberVO mvo);
	int selectEmail(String email);
}
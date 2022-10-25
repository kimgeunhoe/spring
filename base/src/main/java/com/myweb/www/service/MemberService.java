package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.PagingVO;

public interface MemberService {
//	int register(MemberVO mvo);
	int register(MemberDTO mdto);
	MemberVO login(MemberVO mvo);
	List<MemberVO> getList(PagingVO pgvo);
//	MemberVO getDetail(String email);
	MemberDTO getDetail(String email);
//	int modify(MemberVO mvo);
	int modify(MemberDTO mdto);
	int remove(String email);
	int dupleCheck(String email);
	int getTotalCount(PagingVO pgvo);
	int removeProfile(String uuid);
	boolean updateLastLogin(String email);
}

package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.security.AuthVO;

import lombok.NonNull;

public interface MemberDAO {
	int insert(MemberVO mvo);
	MemberVO login(MemberVO mvo);
	List<MemberVO> selectList(PagingVO pgvo);
	MemberVO selectOne(String email);
	int update(MemberVO mvo);
	int delete(String email);
	int selectEmail(String email);
	int selectTotalCount(PagingVO pgvo);
	MemberVO selectAuth(String email);
	List<AuthVO> selectAuthList(String email);
	int insertAuth(@NonNull String email);
	int updateLastLogin(String email);
}
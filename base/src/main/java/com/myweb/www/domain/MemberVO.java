package com.myweb.www.domain;

import java.util.List;

import com.myweb.www.security.AuthVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberVO {
	@NonNull
	private String email;
	@NonNull
	private String pwd;
	@NonNull
	private String nickName;
	private String regAt;
	private String lastLogin;
	private int grade;
	private List<AuthVO> authList;

	public MemberVO(String email, String pwd, String nickName, int grade) {
		this(email, pwd, nickName);
		this.grade = grade;
	}

	public MemberVO(String email, String nickName, String regAt, String lastLogin, int grade) {
		this.email = email;
		this.nickName = nickName;
		this.regAt = regAt;
		this.lastLogin = lastLogin;
		this.grade = grade;
	}

}
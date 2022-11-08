package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.repository.ProfileDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO mdao;

	@Inject
	private ProfileDAO prdao;

	@Override
	public int register(MemberDTO mdto) {
		int isUp = mdao.insert(mdto.getMvo());

		if (isUp > 0 && mdto.getPrvo() != null) {
			mdto.getPrvo().setEmail(mdto.getMvo().getEmail());
			isUp *= prdao.insertProfile(mdto.getPrvo());
		}
		return isUp;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		return mdao.login(mvo);
	}

}

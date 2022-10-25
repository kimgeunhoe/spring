package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.MemberDTO;
import com.myweb.www.domain.MemberVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.repository.ProfileDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO mdao;

	@Inject
	private ProfileDAO prdao;
//	@Override
//	public int register(MemberVO mvo) {
//		return mdao.insert(mvo);
//	}

	@Override
	public int register(MemberDTO mdto) {
		int isUp = mdao.insert(mdto.getMvo());
		mdao.insertAuth(mdto.getMvo().getEmail());

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

	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		return mdao.selectList(pgvo);
	}

//	@Override
//	public MemberVO getDetail(String email) {
//		return mdao.selectOne(email);
//	}

	@Override
	public MemberDTO getDetail(String email) {
		return new MemberDTO(mdao.selectOne(email), prdao.selectProfile(email));
	}

//	@Override
//	public int modify(MemberVO mvo) {
//		return mdao.update(mvo);
//	}

	@Override
	public int modify(MemberDTO mdto) {
		int isUp = mdao.update(mdto.getMvo());

		if (isUp > 0 && mdto.getPrvo() != null) {
			mdto.getPrvo().setEmail(mdto.getMvo().getEmail());
			isUp *= prdao.deleteProfile(mdto.getMvo().getEmail());
			isUp *= prdao.insertProfile(mdto.getPrvo());
		}
		return isUp;
	}

//	@Override
//	public int remove(String email) {
//		return mdao.delete(email);
//	}
	
	@Override
	public int remove(String email) {
		int isUp = mdao.delete(email);
		
		if(isUp > 0) {
			isUp = prdao.deleteProfile(email);
		}
		return isUp;
	}

	@Override
	public int dupleCheck(String email) {
		return mdao.selectEmail(email);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return mdao.selectTotalCount(pgvo);
	}

	@Override
	public int removeProfile(String uuid) {
		return prdao.deleteProfile(uuid);
	}

	@Override
	public boolean updateLastLogin(String email) {
		return mdao.updateLastLogin(email) > 0 ? true : false;
	}

}

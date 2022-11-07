package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProfileVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.ProfileDAO;
import com.myweb.www.repository.BoardDAO;

@Service
public class CommentServiceImpl implements CommentService {
	@Inject
	private CommentDAO cdao;
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private ProfileDAO prdao;
	
	@Transactional
	@Override
	public int post(CommentVO cvo) {
		int isUp = cdao.insert(cvo);
		if(isUp>0) {
			isUp = bdao.updateCmtQty(cvo.getBno(), 1);
		}
		return isUp;
	}

	@Override
	public PagingHandler spread(long bno, PagingVO pgvo) {
		List<CommentVO> cmtList = cdao.selectList(bno, pgvo);
		List<ProfileVO> pfList = new ArrayList<>();
		for(CommentVO list : cmtList) {
			pfList.add(prdao.selectProfile(list.getWriter()));
		}
		return new PagingHandler(cmtList, pgvo, cdao.selectTotalCount(bno), pfList);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Transactional
	@Override
	public int remove(long cno) {
		long bno = cdao.selectBno(cno);
		int isUp = cdao.delete(cno);
		if(isUp>0) {
			isUp = bdao.updateCmtQty(bno, -1);
		}
		return isUp;
	}

}

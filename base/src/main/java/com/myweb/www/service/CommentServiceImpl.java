package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.ProductDAO;

@Service
public class CommentServiceImpl implements CommentService {
	@Inject
	private CommentDAO cdao;
	
	@Inject
	private ProductDAO pdao;
	
	@Transactional
	@Override
	public int post(CommentVO cvo) {
		int isUp = cdao.insert(cvo);
		if(isUp>0) {
			isUp = pdao.updateCmtQty(cvo.getPno(), 1);
		}
		return isUp;
	}

	@Override
	public PagingHandler spread(long pno, PagingVO pgvo) {
		return new PagingHandler(cdao.selectList(pno, pgvo), pgvo, cdao.selectTotalCount(pno));
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Transactional
	@Override
	public int remove(long cno) {
		long pno = cdao.selectPno(cno);
		int isUp = cdao.delete(cno);
		if(isUp>0) {
			isUp = pdao.updateCmtQty(pno, -1);
		}
		return isUp;
	}

}

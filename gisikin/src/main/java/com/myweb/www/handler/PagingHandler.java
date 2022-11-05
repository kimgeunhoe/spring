package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProfileVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;
	private List<CommentVO> cmtList;
	private List<ProfileVO> pfList;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;

		this.endPage = (int)(Math.ceil(pgvo.getPageNo()/(pgvo.getQty()*1.0))) * pgvo.getQty();
		this.startPage = endPage - 9;
		
		int realEndPage = (int)Math.ceil((totalCount * 1.0) / pgvo.getQty());
		
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = startPage > 1;
		this.next = endPage < realEndPage;
	}

	public PagingHandler(List<CommentVO> cmtList, PagingVO pgvo, int totalCount) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
	public PagingHandler(List<CommentVO> cmtList, PagingVO pgvo, int totalCount, List<ProfileVO> pfList) {
		this(cmtList, pgvo, totalCount);
		this.pfList = pfList;
	}
}

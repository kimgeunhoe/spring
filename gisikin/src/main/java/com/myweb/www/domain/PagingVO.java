package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingVO {
	private int pageNo;
	private int qty;
	
	private String type;
	private String kw;
	
	public PagingVO() {
		this(1, 10);
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
}

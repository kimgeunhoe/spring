package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVO {
	private long pno;
	private String category;
	private String pname;
	private int price;
	private String writer;
	private String description;
	private String madeBy;
	private String regAt;
	private String modAt;
	private int readCount;
	private int cmtQty;
	private int fileCount;

	public ProductVO() {}

	public ProductVO(String category, String pname, int price, String writer, String description,
			String madeBy) {
		this.category = category;
		this.pname = pname;
		this.price = price;
		this.writer = writer;
		this.description = description;
		this.madeBy = madeBy;
	}

	public ProductVO(long pno, String category, String pname, int price, String writer, String modAt, int readCount,
			int cmtQty) {
		this.pno = pno;
		this.category = category;
		this.pname = pname;
		this.price = price;
		this.writer = writer;
		this.modAt = modAt;
		this.readCount = readCount;
		this.cmtQty = cmtQty;
	}

	public ProductVO(long pno, String category, String pname, int price, String description,
			String madeBy) {
		this.pno = pno;
		this.category = category;
		this.pname = pname;
		this.price = price;
		this.description = description;
		this.madeBy = madeBy;
	}

	public ProductVO(long pno, String category, String pname, int price, String writer, String description,
			String madeBy, String regAt, String modAt, int readCount, int cmtQty) {
		this.pno = pno;
		this.category = category;
		this.pname = pname;
		this.price = price;
		this.writer = writer;
		this.description = description;
		this.madeBy = madeBy;
		this.regAt = regAt;
		this.modAt = modAt;
		this.readCount = readCount;
		this.cmtQty = cmtQty;
	}
}

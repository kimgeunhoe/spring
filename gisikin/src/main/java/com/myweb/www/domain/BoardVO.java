package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private long bno;
	private String category;
	private String title;
	private String writer;
	private String description;
	private String regAt;
	private String modAt;
	private int readCount;
	private int cmtQty;
	private int fileCount;
}

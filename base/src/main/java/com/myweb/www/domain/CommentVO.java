package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
	private long cno;
	private long pno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	
	public CommentVO() {}
	
	public CommentVO(long pno, String writer, String content) {
		this.pno = pno;
		this.writer = writer;
		this.content = content;
	}

	public CommentVO(long cno, String content) {
		this.cno = cno;
		this.content = content;
	}

	public CommentVO(long cno, long pno, String writer, String content, String regAt, String modAt) {
		this.cno = cno;
		this.pno = pno;
		this.writer = writer;
		this.content = content;
		this.regAt = regAt;
		this.modAt = modAt;
	}
}

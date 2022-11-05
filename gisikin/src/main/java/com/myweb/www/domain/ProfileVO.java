package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private String email;
	private long fileSize;
}

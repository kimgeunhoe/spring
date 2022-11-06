package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.ProfileVO;

import net.coobird.thumbnailator.Thumbnails;

@Component
public class ProfileHandler {
	private final String UP_DIR = "C:\\_java\\lec\\_spring\\uploaded";

	public ProfileVO getProfile(MultipartFile file) {
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folder = new File(UP_DIR, today);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		ProfileVO frvo = new ProfileVO();
		frvo.setSaveDir(today);
		frvo.setFileSize(file.getSize());
		
		String originalFileName = file.getOriginalFilename();
		String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf("\\")+1);
		frvo.setFileName(onlyFileName);
		
		UUID uuid = UUID.randomUUID();
		frvo.setUuid(uuid.toString());
		
		String fullFileName = uuid.toString() + "_" + onlyFileName;
		File storeFile = new File(folder, fullFileName);
		
		try {
			file.transferTo(storeFile);
			if(isImagefile(storeFile)) {
				frvo.setFileType(1);
				File thumbNail = new File(folder, uuid.toString()+"_th_"+onlyFileName);
				Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return frvo;
	}

	private boolean isImagefile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image") ? true : false;
	}
}

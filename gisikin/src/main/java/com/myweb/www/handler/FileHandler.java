package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import net.coobird.thumbnailator.Thumbnails;

@Component
public class FileHandler {
	private final String UP_DIR = "C:\\_java\\lec\\_spring\\uploaded";

	public List<FileVO> getFileList(MultipartFile[] files) {
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folder = new File(UP_DIR, today);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf("\\")+1); //경로제거 후 순수파일 이름만
			fvo.setFileName(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folder, fullFileName);
			
			try {
				file.transferTo(storeFile);
				if(isImagefile(storeFile)) {
					fvo.setFileType(1);
					File thumbNail = new File(folder, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileList.add(fvo);
		}
		return fileList;
	}

	private boolean isImagefile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image") ? true : false;
	}
}

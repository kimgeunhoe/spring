package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.FileDAO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FileSweepHandler {
	private final String UP_DIR = "C:\\_java\\lec\\_spring\\uploaded\\";
	
	@Inject
	private FileDAO fdao;
	
	@Scheduled(cron = "1 59 16 * * *")
	public void fileSweeper() throws Exception {
		List<FileVO> dbFileList = fdao.selectAllFiles();
		
		List<String> savedFileList = new ArrayList<>();
		
		for(FileVO fvo : dbFileList) {
			String filePath = fvo.getSaveDir() + "\\" + fvo.getUuid();
			String fileName = fvo.getFileName();
			savedFileList.add(UP_DIR+filePath+"_"+fileName);
			if(fvo.getFileType()>0) {
				savedFileList.add(UP_DIR+filePath+"_th_"+fileName);
			}
		}
		
		LocalDate now = LocalDate.now();
		String targetDay = now.toString(); //오늘 생성된 파일 지우기
		targetDay = targetDay.replace("_", File.separator);
		
		File dir = Paths.get(UP_DIR + targetDay).toFile();
		File[] allFileObjects = dir.listFiles();
		
		for(File file : allFileObjects) {
			String storeFileName = file.toPath().toString();
			if(!savedFileList.contains(storeFileName)) {
				file.delete();
			}
		}
	}
}

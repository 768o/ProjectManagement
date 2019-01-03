package com.xhSmat.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SaveFileL {

	private SaveFileL(){
		
	}
	public static String save(String path,CommonsMultipartFile file){
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + file.getOriginalFilename();
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();// 保存
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path+"/"+fileName;
	} 
}

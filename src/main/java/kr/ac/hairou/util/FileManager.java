package kr.ac.hairou.util;

import java.io.File;

import kr.ac.hairou.model.Thumbnail;

public interface FileManager {
	final String UPLOAD_PATH = "D:/thumbnail/";
	Thumbnail upload() throws Exception;
	static void delete(Thumbnail item) throws Exception{
		File f = new File(UPLOAD_PATH+item.getFullname());
		
		if(f.exists()) {
			if(!f.delete()) {
				throw new Exception("파일 삭제에 실패했습니다.");
			}
		}
		else {
			throw new NullPointerException("파일이 존재하지 않습니다.");
		}
	}

}

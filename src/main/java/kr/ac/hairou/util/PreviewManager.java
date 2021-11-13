package kr.ac.hairou.util;

import java.io.File;
import java.util.UUID;

import com.google.common.io.Files;

import kr.ac.hairou.model.Thumbnail;

public class PreviewManager implements FileManager {

	@Override
	public Thumbnail upload() throws Exception {
		Thumbnail thumbnail = new Thumbnail();
		thumbnail.setFilename("preview.jpg");
		UUID uuid = UUID.randomUUID();
		thumbnail.setUuid(uuid.toString());
		int rand = (int)(Math.random()*17);
		File file = new File(UPLOAD_PATH+"preview"+rand+".jpg");
		File newFile = new File(UPLOAD_PATH+thumbnail.getFullname());
		Files.copy(file, newFile);
		
		return thumbnail;
	}

}

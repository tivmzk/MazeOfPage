package kr.ac.hairou.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kr.ac.hairou.model.Thumbnail;

public class ThumbnailManager implements FileManager {
	private MultipartFile image;
	public ThumbnailManager(MultipartFile image) {
		this.image = image;
	}

	@Override
	public Thumbnail upload() throws Exception{
		Thumbnail thumbnail = new Thumbnail();
		thumbnail.setFilename(image.getOriginalFilename());
		UUID uuid = UUID.randomUUID();
		thumbnail.setUuid(uuid.toString());
		image.transferTo(new File(UPLOAD_PATH+thumbnail.getFullname()));
		return thumbnail;
	}

}

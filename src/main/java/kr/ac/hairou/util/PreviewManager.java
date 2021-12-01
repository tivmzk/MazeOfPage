package kr.ac.hairou.util;

import kr.ac.hairou.model.Thumbnail;

public class PreviewManager implements FileManager {

	@Override
	public Thumbnail upload() throws Exception {
		Thumbnail thumbnail = new Thumbnail();
		
		int rand = (int)(Math.random()*17);
		thumbnail.setFilename(String.format("preview%d.jpg", rand));
		
		return thumbnail;
	}

}

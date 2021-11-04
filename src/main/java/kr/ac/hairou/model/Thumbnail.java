package kr.ac.hairou.model;

public class Thumbnail {
	private int novel;
	private String uuid;
	private String filename;
	
	public int getNovel() {
		return novel;
	}
	public void setNovel(int novel) {
		this.novel = novel;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFullname() {
		return String.format("%s_%s", uuid, filename);
	}
}

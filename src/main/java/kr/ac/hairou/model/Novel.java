package kr.ac.hairou.model;

import java.util.Date;

public class Novel {
	private int code;
	private String member;
	private int genre;
	private String title;
	private String info;
	private int recom;
	private Date date;
	
	private String nickname;
	private String genreContents;
	private int bookmark;
	
	private Thumbnail image;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getBookmark() {
		return bookmark;
	}
	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}
	public Thumbnail getImage() {
		return image;
	}
	public void setImage(Thumbnail image) {
		this.image = image;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public String getGenreContents() {
		return genreContents;
	}
	public void setGenreContents(String genreContents) {
		this.genreContents = genreContents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getRecom() {
		return recom;
	}
	public void setRecom(int recom) {
		this.recom = recom;
	}
	public String getPreview() {
		return String.format("preview%d.jpg", (int)(Math.random()*17));
	}
}

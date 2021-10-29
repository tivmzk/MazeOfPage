package kr.ac.hairou.model;

public class Novel {
	private int code;
	private String member;
	private int genre;
	private String title;
	private String info;
	private int recom;
	
	private int rank;
	private String nickname;
	private String genreContents;
	
	private Thumbnail thumbnail;
	
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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

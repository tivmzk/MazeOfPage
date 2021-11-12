package kr.ac.hairou.model;

import java.util.Date;

public class Review {
	private int code;
	private String member;
	private int novel;
	private String title;
	private String contents;
	private Date date;
	
	private String novelTitle;
	private String nickname;
	
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

	public int getNovel() {
		return novel;
	}

	public void setNovel(int novel) {
		this.novel = novel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNovelTitle() {
		return novelTitle;
	}

	public void setNovelTitle(String novelTitle) {
		this.novelTitle = novelTitle;
	}
}

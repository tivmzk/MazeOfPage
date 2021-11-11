package kr.ac.hairou.model;

import java.util.List;

public class Profile {
	private String member;
	private String contents;
	
	private String nickname;
	private int recom;
	private List<Novel> novelList;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRecom() {
		return recom;
	}
	public void setRecom(int recom) {
		this.recom = recom;
	}
	public List<Novel> getNovelList() {
		return novelList;
	}
	public void setNovelList(List<Novel> novelList) {
		this.novelList = novelList;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}

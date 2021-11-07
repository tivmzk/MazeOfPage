package kr.ac.hairou.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Episode {
	private int code;
	private int novel;
	private String title;
	private String contents;
	private char isStart;
	private Date date;
	
	private String member;
	private List<Option> options = new ArrayList<Option>();
	
	
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	public char getIsStart() {
		return isStart;
	}
	public void setIsStart(char isStart) {
		this.isStart = isStart;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

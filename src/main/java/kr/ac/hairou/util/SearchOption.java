package kr.ac.hairou.util;

public class SearchOption {
	private int option;
	private int genre;
	private String keyword;
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public SearchOption(int option, int genre) {
		this.option = option;
		this.genre = genre;
	}
	public SearchOption(int option) {
		this.option = option;
	}
	public SearchOption() {
		option = 0;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}
}

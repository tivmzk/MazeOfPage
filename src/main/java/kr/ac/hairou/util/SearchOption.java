package kr.ac.hairou.util;

public class SearchOption {
	private int option;
	private int genre;
	private String keyword;
	
	private int amount;
	private int offset;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	
	public SearchOption(int option, int genre, int amount, int offset) {
		this.option = option;
		this.genre = genre;
		this.amount = amount;
		this.offset = offset;
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
}

package kr.ac.hairou.util;

public class SearchOption {
	private int option;
	private int genre;
	
	
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

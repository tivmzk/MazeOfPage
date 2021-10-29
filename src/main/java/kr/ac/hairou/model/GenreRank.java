package kr.ac.hairou.model;

import java.util.List;

public class GenreRank {
	private String genreContents;
	private List<Novel> list;
	
	public String getGenreContents() {
		return genreContents;
	}
	public void setGenreContents(String genreContents) {
		this.genreContents = genreContents;
	}
	public List<Novel> getList() {
		return list;
	}
	public void setList(List<Novel> list) {
		this.list = list;
	}
	
	public GenreRank() {
		// TODO Auto-generated constructor stub
	}
	public GenreRank(String genre, List<Novel> list) {
		this.genreContents = genre;
		this.list = list;
	}
}

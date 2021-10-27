package kr.ac.hairou.model;

public class Genre {
	private int code;
	private String contents;
	private int recomTotal;
	
	public int getRecomTotal() {
		return recomTotal;
	}
	public void setRecomTotal(int recomTotal) {
		this.recomTotal = recomTotal;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}

package kr.ac.hairou.model;

public class Member {
	private String id;
	private String password;
	private String nickname;
	private boolean mgr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isMgr() {
		return mgr;
	}
	public void setMgr(boolean mgr) {
		this.mgr = mgr;
	}
}

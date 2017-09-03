package com.micro.profession.mybatis;

public class User {

	private int id;
	private String userName;
	private String corp;
	
	public User(int id, String userName, String corp) {
		this.id = id;
		this.userName = userName;
		this.corp = corp;
	}

	public User(String userName, String corp) {
		super();
		this.userName = userName;
		this.corp = corp;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCorp() {
		return corp;
	}
	
	public void setCorp(String corp) {
		this.corp = corp;
	}
	
	
}

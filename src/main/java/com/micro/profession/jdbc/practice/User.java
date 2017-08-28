package com.micro.profession.jdbc.practice;

public class User {

	// 用户id
	private int id;
	// 用户名
	private String userName;
	// 性别
	private Boolean sex;
	// 密码
	private String password;
	
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
	
	public Boolean getSex() {
		return sex;
	}
	
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}

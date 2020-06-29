package com.dianbao.domain;

/**
 * User映射类
 * 
 * @author yyl
 * @since 2020年6月3日
 */
public class User {
	private Integer userId;
	private String userTel;
	private String userName;
	private String userPassword;
	private String userEmail;
 
	public Integer getUserId() {
		return userId;
	}
 
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
 
	public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public String getUserPassword() {
		return userPassword;
	}
 
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
 
	public String getUserEmail() {
		return userEmail;
	}
 
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
 
	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userTel=" + userTel+ ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userEmail=" + userEmail
				+ "]";
	}
	
}
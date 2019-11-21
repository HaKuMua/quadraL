package com.zj.entity;

import java.io.Serializable;

/**
 * 
 * @author lijia
 * 用户实体类
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 用户姓名
	 */
	private String user_name;
	/*
	 * 用户头像URL
	 */
	private String user_headimg_url;
	/*
	 * 用户邮箱
	 */
	private String user_email;
	/*
	 * 用户手机号
	 */
	private String user_phone;
	/*
	 * 用户身份证
	 */
	private String user_IDcard;
	
	public User() {
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_headimg_url() {
		return user_headimg_url;
	}

	public void setUser_headimg_url(String user_headimg_url) {
		this.user_headimg_url = user_headimg_url;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_IDcard() {
		return user_IDcard;
	}

	public void setUser_IDcard(String user_IDcard) {
		this.user_IDcard = user_IDcard;
	}

	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_headimg_url=" + user_headimg_url + ", user_email="
				+ user_email + ", user_phone=" + user_phone + ", user_IDcard="
				+ user_IDcard + "]";
	}
}

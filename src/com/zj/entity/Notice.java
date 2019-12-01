package com.zj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author lijia
 *通知实体类
 */
public class Notice implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 通知ID
	 */
	private Integer notice_id;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 通知内容
	 */
	private String notice_content;
	
	/**
	 * 通知时间
	 */
	private Date notice_date;
	
	/**
	 * 通知状态
	 */
	private Integer notice_status;
	
	public Notice() {
	
	}

	public Integer getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public Integer getNotice_status() {
		return notice_status;
	}

	public void setNotice_status(Integer notice_status) {
		this.notice_status = notice_status;
	}

	@Override
	public String toString() {
		return "Notice [notice_id=" + notice_id + ", user_id=" + user_id
				+ ", notice_content=" + notice_content + ", notice_date="
				+ notice_date + ", notice_status=" + notice_status + "]";
	}

	
}

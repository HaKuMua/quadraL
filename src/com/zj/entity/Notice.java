package com.zj.entity;

import java.io.Serializable;

/**
 * 
 * @author lijia
 *须知实体类
 */
public class Notice implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 须知ID
	 */
	private Integer notice_id;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	/*
	 * 须知名称
	 */
	private String notice_name;
	/*
	 * 须知内容
	 */
	private String notice_describe;
	
	public Notice() {
	}

	public Integer getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String getNotice_name() {
		return notice_name;
	}

	public void setNotice_name(String notice_name) {
		this.notice_name = notice_name;
	}

	public String getNotice_describe() {
		return notice_describe;
	}

	public void setNotice_describe(String notice_describe) {
		this.notice_describe = notice_describe;
	}

	public String toString() {
		return "Notice [notice_id=" + notice_id + ", house_id=" + house_id
				+ ", notice_name=" + notice_name + ", notice_describe="
				+ notice_describe + "]";
	}
	
}

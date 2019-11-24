package com.zj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author lijia
 *房子评论实体类
 */
public class HouseComment implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 房子评论ID
	 */
	private Integer houseCom_id;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 评论内容
	 */
	private String houseCom_content;
	/*
	 * 房子评论时间
	 */
	private Date houseCom_date;
	/*
	 * 回复ID
	 */
	private String replier_id;
	
	public HouseComment() {
	}

	public Integer getHouseCom_id() {
		return houseCom_id;
	}

	public void setHouseCom_id(Integer houseCom_id) {
		this.houseCom_id = houseCom_id;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getHouseCom_content() {
		return houseCom_content;
	}

	public void setHouseCom_content(String houseCom_content) {
		this.houseCom_content = houseCom_content;
	}

	public Date getHouseCom_date() {
		return houseCom_date;
	}

	public void setHouseCom_date(Date houseCom_date) {
		this.houseCom_date = houseCom_date;
	}

	public String getReplier_id() {
		return replier_id;
	}

	public void setReplier_id(String replier_id) {
		this.replier_id = replier_id;
	}

	public String toString() {
		return "HouseComment [houseCom_id=" + houseCom_id + ", house_id="
				+ house_id + ", user_id=" + user_id + ", houseCom_content="
				+ houseCom_content + ", houseCom_date=" + houseCom_date
				+ ", replier_id=" + replier_id + "]";
	}
}

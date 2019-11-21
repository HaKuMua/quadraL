package com.zj.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author lijia
 *预定信息实体类
 */
public class Reserve implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 预定ID
	 */
	private Integer reserve_id;
	/*
	 * 预定时间
	 */
	private Date reserve_date;
	/*
	 * 预定天数
	 */
	private Integer reserve_day_number;
	/*
	 * 退房时间
	 */
	private Date check_out_date;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	
	public Reserve() {
	}

	public Integer getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}

	public Date getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(Date reserve_date) {
		this.reserve_date = reserve_date;
	}

	public Integer getReserve_day_number() {
		return reserve_day_number;
	}

	public void setReserve_day_number(Integer reserve_day_number) {
		this.reserve_day_number = reserve_day_number;
	}

	public Date getCheck_out_date() {
		return check_out_date;
	}

	public void setCheck_out_date(Date check_out_date) {
		this.check_out_date = check_out_date;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String toString() {
		return "Reserve [reserve_id=" + reserve_id + ", reserve_date="
				+ reserve_date + ", reserve_day_number=" + reserve_day_number
				+ ", check_out_date=" + check_out_date + ", user_id=" + user_id
				+ ", house_id=" + house_id + "]";
	}
}

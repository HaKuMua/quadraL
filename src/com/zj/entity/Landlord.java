package com.zj.entity;

import java.io.Serializable;
/**
 * 
 * @author lijia
 *房东实体类
 */
public class Landlord implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 房东ID
	 */
	private Integer landlord_id;
	/*
	 * 房东头像URL
	 */
	private String landlord_headimg_url;
	/*
	 * 房东姓名
	 */
	private String landlord_name;
	/*
	 * 房东签名
	 */
	private String landlord_describe;
	/*
	 * 房东身份证
	 */
	private String landlord_IDcard;
	/*
	 * 房东邮箱
	 */
	private String landlord_email;
	/*
	 * 房东手机号
	 */
	private String landlord_phone;
	/*
	 * 提前多少天通知顾客入住
	 */
	private Integer inform_date;
	/*
	 * 真实姓名
	 */
	private String real_name;
	
	/*
	 * 房东密码
	 */
	private String landlord_pwd;
	
	public Landlord() {
	}

	public Integer getLandlord_id() {
		return landlord_id;
	}

	public void setLandlord_id(Integer landlord_id) {
		this.landlord_id = landlord_id;
	}

	public String getLandlord_headimg_url() {
		return landlord_headimg_url;
	}

	public void setLandlord_headimg_url(String landlord_headimg_url) {
		this.landlord_headimg_url = landlord_headimg_url;
	}

	public String getLandlord_name() {
		return landlord_name;
	}

	public void setLandlord_name(String landlord_name) {
		this.landlord_name = landlord_name;
	}

	public String getLandlord_describe() {
		return landlord_describe;
	}

	public void setLandlord_describe(String landlord_describe) {
		this.landlord_describe = landlord_describe;
	}

	public String getLandlord_IDcard() {
		return landlord_IDcard;
	}

	public void setLandlord_IDcard(String landlord_IDcard) {
		this.landlord_IDcard = landlord_IDcard;
	}

	public String getLandlord_email() {
		return landlord_email;
	}

	public void setLandlord_email(String landlord_email) {
		this.landlord_email = landlord_email;
	}

	public String getLandlord_phone() {
		return landlord_phone;
	}

	public void setLandlord_phone(String landlord_phone) {
		this.landlord_phone = landlord_phone;
	}

	public Integer getInform_date() {
		return inform_date;
	}

	public void setInform_date(Integer inform_date) {
		this.inform_date = inform_date;
	}
	
	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	
	public String getLandlord_pwd() {
		return landlord_pwd;
	}

	public void setLandlord_pwd(String landlord_pwd) {
		this.landlord_pwd = landlord_pwd;
	}

	public String toString() {
		return "Landlord [landlord_id=" + landlord_id
				+ ", landlord_headimg_url=" + landlord_headimg_url
				+ ", landlord_name=" + landlord_name + ", landlord_describe="
				+ landlord_describe + ", landlord_IDcard=" + landlord_IDcard
				+ ", landlord_email=" + landlord_email + ", landlord_phone="
				+ landlord_phone + ", inform_date=" + inform_date
				+ ", real_name=" + real_name + ", landlord_pwd=" + landlord_pwd
				+ "]";
	}

	
}

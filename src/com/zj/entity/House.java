package com.zj.entity;

import java.io.Serializable;
import java.sql.Time;
/**
 * 
 * @author lijia
 *房子实体类
 */
public class House implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 房子ID
	 */
	private Integer house_id;
	/**
	 * 房子名称
	 */
	private String house_name;
	/**
	 * 房子容纳量
	 */
	private Integer house_intake;
	/**
	 * 出租类型
	 */
	private String lease_type;
	/**
	 * 可入住时间
	 */
	private String may_check_in_date;
	/**
	 * 可退房时间
	 */
	private String may_check_out_date;
	/**
	 * 房子类型
	 */
	private String house_type;
	/**
	 * 房子详情ID
	 */
	private Integer house_particulars_id;
	/**
	 * 房子状态
	 */
	private Integer house_state;
	/**
	 * 出行信息
	 */
	private String travel_information;
	/**
	 * 房子价格
	 */
	private Double house_price;
	/**
	 * 房子地址
	 */
	private String house_address;
	/**
	 * 用户ID
	 */
	private Integer user_id;
	/**
	 * 门牌号
	 */
	private String location_id;
	
	public House() {
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String getHouse_name() {
		return house_name;
	}

	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}

	public Integer getHouse_intake() {
		return house_intake;
	}

	public void setHouse_intake(Integer house_intake) {
		this.house_intake = house_intake;
	}

	public String getLease_type() {
		return lease_type;
	}

	public void setLease_type(String lease_type) {
		this.lease_type = lease_type;
	}

	public String getMay_check_in_date() {
		return may_check_in_date;
	}

	public void setMay_check_in_date(String may_check_in_date) {
		this.may_check_in_date = may_check_in_date;
	}

	public String getMay_check_out_date() {
		return may_check_out_date;
	}

	public void setMay_check_out_date(String may_check_out_date) {
		this.may_check_out_date = may_check_out_date;
	}

	public String getHouse_type() {
		return house_type;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public Integer getHouse_particulars_id() {
		return house_particulars_id;
	}

	public void setHouse_particulars_id(Integer house_particulars_id) {
		this.house_particulars_id = house_particulars_id;
	}

	public Integer getHouse_state() {
		return house_state;
	}

	public void setHouse_state(Integer house_state) {
		this.house_state = house_state;
	}

	public String getTravel_information() {
		return travel_information;
	}

	public void setTravel_information(String travel_information) {
		this.travel_information = travel_information;
	}

	public Double getHouse_price() {
		return house_price;
	}

	public void setHouse_price(Double house_price) {
		this.house_price = house_price;
	}

	public String getHouse_address() {
		return house_address;
	}

	public void setHouse_address(String house_address) {
		this.house_address = house_address;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String toString() {
		return "House [house_id=" + house_id + ", house_name=" + house_name
				+ ", house_intake=" + house_intake + ", lease_type="
				+ lease_type + ", may_check_in_date=" + may_check_in_date
				+ ", may_check_out_date=" + may_check_out_date
				+ ", house_type=" + house_type + ", house_particulars_id="
				+ house_particulars_id + ", house_state=" + house_state
				+ ", travel_information=" + travel_information
				+ ", house_price=" + house_price + ", house_address="
				+ house_address + ", user_id=" + user_id + ", location_id="
				+ location_id + "]";
	}
}

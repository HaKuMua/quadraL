package com.zj.entity;

import java.io.Serializable;

/**
 * 
 * @author lijia
 *房子详情实体类
 */
public class HouseParticulars implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 房子详情ID
	 */
	private Integer house_particulars_id;
	/**
	 * 房间数量
	 */
	private Integer room_number;
	/**
	 * 地址描述
	 */
	private String address_describe;
	/**
	 * 卫生间数量
	 */
	private Integer toilet_number;
	/**
	 * 房子详情
	 */
	private String house_describe;
	
	public HouseParticulars() {
	}

	public Integer getHouse_particulars_id() {
		return house_particulars_id;
	}

	public void setHouse_particulars_id(Integer house_particulars_id) {
		this.house_particulars_id = house_particulars_id;
	}

	public Integer getRoom_number() {
		return room_number;
	}

	public void setRoom_number(Integer room_number) {
		this.room_number = room_number;
	}

	public String getAddress_describe() {
		return address_describe;
	}

	public void setAddress_describe(String address_describe) {
		this.address_describe = address_describe;
	}

	public Integer getToilet_number() {
		return toilet_number;
	}

	public void setToilet_number(Integer toilet_number) {
		this.toilet_number = toilet_number;
	}

	public String getHouse_describe() {
		return house_describe;
	}

	public void setHouse_describe(String house_describe) {
		this.house_describe = house_describe;
	}

	public String toString() {
		return "HouseParticulars [house_particulars_id=" + house_particulars_id
				+ ", room_number=" + room_number + ", address_describe="
				+ address_describe + ", toilet_number=" + toilet_number
				+ ", house_describe=" + house_describe + "]";
	}
}

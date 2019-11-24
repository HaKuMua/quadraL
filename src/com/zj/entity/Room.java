package com.zj.entity;

import java.io.Serializable;
/**
 * 
 * @author lijia 
 *房间实体类
 */
public class Room implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 房间ID
	 */
	private Integer room_id;
	/*
	 * 房间类型
	 */
	private String room_type;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	/*
	 * 床类型
	 */
	private String bed_type;
	/*
	 * 床数量
	 */
	private Integer bed_number;
	
	public Room() {
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String getBed_type() {
		return bed_type;
	}

	public void setBed_type(String bed_type) {
		this.bed_type = bed_type;
	}

	public Integer getBed_number() {
		return bed_number;
	}

	public void setBed_number(Integer bed_number) {
		this.bed_number = bed_number;
	}

	public String toString() {
		return "Room [room_id=" + room_id + ", room_type=" + room_type
				+ ", house_id=" + house_id + ", bed_type=" + bed_type
				+ ", bed_number=" + bed_number + "]";
	}
}

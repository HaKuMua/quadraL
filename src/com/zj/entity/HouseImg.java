package com.zj.entity;

import java.io.Serializable;
/**
 * 
 * @author lijia
 *房子图片实体类
 */
public class HouseImg implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 房子图片ID
	 */
	private Integer house_img_id;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	/*
	 * 房子图片URL
	 */
	private String house_img_url;
	
	public HouseImg() {
	}

	public Integer getHouse_img_id() {
		return house_img_id;
	}

	public void setHouse_img_id(Integer house_img_id) {
		this.house_img_id = house_img_id;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String getHouse_img_url() {
		return house_img_url;
	}

	public void setHouse_img_url(String house_img_url) {
		this.house_img_url = house_img_url;
	}

	public String toString() {
		return "HouseImg [house_img_id=" + house_img_id + ", house_id="
				+ house_id + ", house_img_url=" + house_img_url + "]";
	}
	
	
}

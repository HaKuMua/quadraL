
package com.zj.entity;

import java.io.Serializable;
/**
 * 
 * @author lijia
 * 设施实体类
 *
 */
public class Facilities implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 设施ID
	 */
	private Integer facilities_id;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	/*
	 * 设施名
	 */
	private String facilities_name;
	/*
	 * 设施图片
	 */
	private String facilities_img;
	
	public Facilities() {
	}

	public Integer getFacilities_id() {
		return facilities_id;
	}

	public void setFacilities_id(Integer facilities_id) {
		this.facilities_id = facilities_id;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String getFacilities_name() {
		return facilities_name;
	}

	public void setFacilities_name(String facilities_name) {
		this.facilities_name = facilities_name;
	}

	public String getFacilities_img() {
		return facilities_img;
	}

	public void setFacilities_img(String facilities_img) {
		this.facilities_img = facilities_img;
	}
	
	public String toString() {
		return "Facilities [facilities_id=" + facilities_id + ", house_id="
				+ house_id + ", facilities_name=" + facilities_name
				+ ", facilities_img=" + facilities_img + "]";
	}
	
	
}


package com.zj.entity;

import java.io.Serializable;

/**
 * 
 * @author lijia
 *房屋守则实体类
 */
public class BuildingCodes implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 房屋守则ID
	 */
	private Integer building_codes_id;
	/*
	 * 房子ID
	 */
	private Integer house_id;
	/*
	 * 守则名称
	 */
	private String building_name;
	/*
	 * 守则状态
	 */
	private String rules_state;
	
	public BuildingCodes() {
	}

	public Integer getBuilding_codes_id() {
		return building_codes_id;
	}

	public void setBuilding_codes_id(Integer building_codes_id) {
		this.building_codes_id = building_codes_id;
	}

	public Integer getHouse_id() {
		return house_id;
	}

	public void setHouse_id(Integer house_id) {
		this.house_id = house_id;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

	public String getRules_state() {
		return rules_state;
	}

	public void setRules_state(String rules_state) {
		this.rules_state = rules_state;
	}

	public String toString() {
		return "BuildingCodes [building_codes_id=" + building_codes_id
				+ ", house_id=" + house_id + ", building_name=" + building_name
				+ ", rules_state=" + rules_state + "]";
	}
	
	
}

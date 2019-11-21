package com.zj.entity;

import java.io.Serializable;
/**
 * 
 * @author lijia
 * 入住人实体类
 *
 */
public class CheckInPerson implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 入住人ID
	 */
	private Integer check_in_person_id;
	/*
	 * 酒店订单ID
	 */
	private Integer grogshop_order_id;
	/*
	 * 入住人姓名
	 */
	private String check_in_person_name;
	/*
	 * 入住人身份证号
	 */
	private String check_in_person_ID_card;
	
	public CheckInPerson() {
	}

	public Integer getCheck_in_person_id() {
		return check_in_person_id;
	}

	public void setCheck_in_person_id(Integer check_in_person_id) {
		this.check_in_person_id = check_in_person_id;
	}

	public Integer getGrogshop_order_id() {
		return grogshop_order_id;
	}

	public void setGrogshop_order_id(Integer grogshop_order_id) {
		this.grogshop_order_id = grogshop_order_id;
	}

	public String getCheck_in_person_name() {
		return check_in_person_name;
	}

	public void setCheck_in_person_name(String check_in_person_name) {
		this.check_in_person_name = check_in_person_name;
	}

	public String getCheck_in_person_ID_card() {
		return check_in_person_ID_card;
	}

	public void setCheck_in_person_ID_card(String check_in_person_ID_card) {
		this.check_in_person_ID_card = check_in_person_ID_card;
	}

	@Override
	public String toString() {
		return "CheckInPerson [check_in_person_id=" + check_in_person_id
				+ ", grogshop_order_id=" + grogshop_order_id
				+ ", check_in_person_name=" + check_in_person_name
				+ ", check_in_person_ID_card=" + check_in_person_ID_card + "]";
	}
}

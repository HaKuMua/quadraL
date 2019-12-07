package com.zj.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author lijia
 *订单实体类
 */
public class GrogshopOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 订单ID
	 */
	private String grogshop_order_id;
	/**
	 * 用户ID
	 */
	private Integer	user_id;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 下单时间
	 */
	private Date place_an_order_date;
	/**
	 * 订单状态
	 */
	private String grogshop_order_state;
	/**
	 * 订单状态
	 */
	private String grogshop_order_landlordState;
	/**
	 * 订单详情
	 */
	private String grogshop_order_describe;
	/**
	 * 预定ID
	 */
	private Integer reserve_id;
	
	public GrogshopOrder() {
	}

	public String getGrogshop_order_id() {
		return grogshop_order_id;
	}

	public void setGrogshop_order_id(String grogshop_order_id) {
		this.grogshop_order_id = grogshop_order_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPlace_an_order_date() {
		return place_an_order_date;
	}

	public void setPlace_an_order_date(Date place_an_order_date) {
		this.place_an_order_date = place_an_order_date;
	}

	public String getGrogshop_order_state() {
		return grogshop_order_state;
	}

	public void setGrogshop_order_state(String grogshop_order_state) {
		this.grogshop_order_state = grogshop_order_state;
	}

	
	public String getGrogshop_order_landlordState() {
		return grogshop_order_landlordState;
	}

	public void setGrogshop_order_landlordState(String grogshop_order_landlordState) {
		this.grogshop_order_landlordState = grogshop_order_landlordState;
	}

	public String getGrogshop_order_describe() {
		return grogshop_order_describe;
	}

	public void setGrogshop_order_describe(String grogshop_order_describe) {
		this.grogshop_order_describe = grogshop_order_describe;
	}

	public Integer getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Integer reserve_id) {
		this.reserve_id = reserve_id;
	}

	public String toString() {
		return "GrogshopOrder [grogshop_order_id=" + grogshop_order_id
				+ ", user_id=" + user_id + ", price=" + price
				+ ", place_an_order_date=" + place_an_order_date
				+ ", grogshop_order_state=" + grogshop_order_state
				+ ", grogshop_order_describe=" + grogshop_order_describe
				+ ", reserve_id=" + reserve_id + "]";
	}
}

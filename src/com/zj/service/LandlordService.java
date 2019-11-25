package com.zj.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.uitl.CheckoutEmail;
import cn.com.uitl.CheckoutIDCard;
import cn.com.uitl.CheckoutPhoneNumber;

import com.zj.control.LandlordControl;
import com.zj.entity.Landlord;

/**
 * 
 * @author lijia
 *房东服务层类
 */
public class LandlordService {
	private LandlordControl landLordControl = new LandlordControl();
	/*
	 * 将所有房东信息包装成list<map>返回
	 */
	public List<Map<String, Object>> getAllLandlordInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<Landlord> allLandlord = landLordControl.getAllLandlordInfo();
			if(allLandlord != null){
				list = new ArrayList<Map<String,Object>>();
				for(Landlord landlord : allLandlord){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("landlord_id", landlord.getLandlord_id());
					map.put("landlord_headimg_url", landlord.getLandlord_headimg_url());
					map.put("landlord_name", landlord.getLandlord_name());
					map.put("landlord_describe", landlord.getLandlord_describe());
					map.put("landlord_IDcard", landlord.getLandlord_IDcard());
					map.put("landlord_email", landlord.getLandlord_email());
					map.put("landlord_phone", landlord.getLandlord_phone());
					map.put("inform_date", landlord.getInform_date());
					map.put("real_name", landlord.getReal_name());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 将单个房东信息包装成map返回
	 */
	public Map<String, Object> getLandlordInfoByPhone(String landlord_phone){
		Map<String, Object> map = null;
		try {
			Landlord landlord = landLordControl.getLandlordInfoByPhone(landlord_phone);
			if(landlord !=null){
				map = new HashMap<String, Object>();
				map.put("landlord_id", landlord.getLandlord_id());
				map.put("landlord_headimg_url", landlord.getLandlord_headimg_url());
				map.put("landlord_name", landlord.getLandlord_name());
				map.put("landlord_describe", landlord.getLandlord_describe());
				map.put("landlord_IDcard", landlord.getLandlord_IDcard());
				map.put("landlord_email", landlord.getLandlord_email());
				map.put("landlord_phone", landlord.getLandlord_phone());
				map.put("inform_date", landlord.getInform_date());
				map.put("real_name", landlord.getReal_name());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public String addLandlordInfo(Map<String, Object> map){
		Landlord landlord = new Landlord();
		if(!CheckoutEmail.checkEmail((String) map.get("landlord_email"))){
			return "邮箱有误";
		}
		try {
			if(!CheckoutIDCard.IDCardValidate((String) map.get("landlord_IDcard"))){
				return "身份证有误";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!CheckoutPhoneNumber.isPhoneNumberValid((String) map.get("landlord_phone"))){
			return "手机号有误";
		}
		landlord.setInform_date((Integer) map.get("inform_date"));
		landlord.setLandlord_describe((String) map.get("landlord_describe"));
		landlord.setLandlord_email((String) map.get("landlord_email"));
		landlord.setLandlord_headimg_url((String) map.get("landlord_headimg_url"));
		landlord.setLandlord_id((Integer) map.get("landlord_id"));
		landlord.setLandlord_IDcard((String) map.get("landlord_IDcard"));
		landlord.setLandlord_name((String) map.get("landlord_name"));
		landlord.setLandlord_phone((String) map.get("landlord_phone"));
		landlord.setReal_name((String) map.get("real_name"));
		try {
			if(landLordControl.addLandlordInfo(landlord)){
				return "插入成功";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "插入失败";
	}
}

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

import com.zj.dao.impl.LandlordDaoImpl;
import com.zj.dao.realize.LandlordDao;
import com.zj.entity.Landlord;
import com.zj.service.imp.LandlordServiceImpl;

/**
 * 
 * @author lijia
 *房东服务层类
 */
public class LandlordService implements LandlordServiceImpl{
	private LandlordDaoImpl landlordDaoImpl = new LandlordDao();
	/**
	 * 将所有房东信息包装成list<map>返回
	 */
	public List<Map<String, Object>> getAllLandlordInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<Landlord> allLandlord = landlordDaoImpl.getAllLandlordInfo();
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
					map.put("landlord_pwd", landlord.getLandlord_pwd());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 将单个房东信息包装成map返回
	 * 按照房东电话号码返回信息
	 */
	public Map<String, Object> getLandlordInfoByPhone(String landlord_phone){
		Map<String, Object> map = null;
		try {
			Landlord landlord = landlordDaoImpl.getLandlordInfoByPhone(landlord_phone);
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
				map.put("landlord_pwd", landlord.getLandlord_pwd());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加一个房东信息
	 * @param map
	 * @return
	 */
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
		if(!CheckoutPhoneNumber.isPhoneNumberValid(map.get("landlord_phone").toString())){
			return "手机号有误";
		}
		try{
		landlord.setInform_date(Integer.valueOf((String) map.get("inform_date")) );
		landlord.setLandlord_describe(map.get("landlord_describe").toString());
		landlord.setLandlord_email(map.get("landlord_email").toString());
		landlord.setLandlord_headimg_url(map.get("landlord_headimg_url").toString());
		landlord.setLandlord_id(Integer.valueOf((String) map.get("landlord_id")) );
		landlord.setLandlord_IDcard(map.get("landlord_IDcard").toString());
		landlord.setLandlord_name(map.get("landlord_name").toString());
		landlord.setLandlord_phone( map.get("landlord_phone").toString());
		landlord.setReal_name( map.get("real_name").toString());
		landlord.setLandlord_pwd( map.get("landlord_pwd").toString());
		}catch (Exception e) {
			e.printStackTrace();
			return "输入有误";
		}
		try {
			if(landlordDaoImpl.addLandlordInfo(landlord)){
				return "插入成功";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "插入失败";
	}
}

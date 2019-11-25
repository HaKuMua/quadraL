package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.control.CheckInPersonControl;
import com.zj.entity.CheckInPerson;
import com.zj.entity.House;
import com.zj.entity.HouseImg;
import com.zj.entity.HouseParticulars;
import com.zj.entity.User;

/**
 * 入住人员服务层类
 * @author LanceEdward
 *
 */
public class CheckInPersonService {
	private CheckInPersonControl cipControl = new CheckInPersonControl();
	/*
	 * 将所有入住人员信息包装成一个list<map>返回
	 */
	public List<Map<String, Object>> getAllCheckInPersonInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<CheckInPerson> checkInPersonList = cipControl.getAllCheckInPersonInfo();
			if(checkInPersonList != null){
				list = new ArrayList<Map<String,Object>>();
				for(CheckInPerson checkInPerson : checkInPersonList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("check_in_person_id", checkInPerson.getCheck_in_person_id());
					map.put("grogshop_order_id", checkInPerson.getGrogshop_order_id());
					map.put("check_in_person_name", checkInPerson.getCheck_in_person_name());
					map.put("check_in_person_ID_card", checkInPerson.getCheck_in_person_ID_card());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 将单个入住人员信息包装成map返回
	 */
	public Map<String, Object> getCheckInPersonInfoByIdCard(String check_in_person_ID_card){
		Map<String, Object> map = null;
		try {
			CheckInPerson person = cipControl.getCheckInPersonInfoByIdCard(check_in_person_ID_card);
			if(person != null){
				map = new HashMap<String, Object>();
				map.put("check_in_person_id", person.getCheck_in_person_id());
				map.put("grogshop_order_id", person.getGrogshop_order_id());
				map.put("check_in_person_name", person.getCheck_in_person_name());
				map.put("check_in_person_ID_card", person.getCheck_in_person_ID_card());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}

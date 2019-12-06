package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.util.PageUtil;

import com.zj.dao.CheckInPersonDao;
import com.zj.dao.impl.CheckInPersonDaoImpl;
import com.zj.entity.CheckInPerson;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.Reserve;
import com.zj.service.impl.CheckInPersonServiceImpl;


/**
 * 入住人员服务层类
 * @author LanceEdward
 *
 */
public class CheckInPersonService implements CheckInPersonServiceImpl{
	private CheckInPersonDaoImpl cPDaoImpl = new CheckInPersonDao();
	/**
	 * 将所有入住人员信息包装成一个<map>返回
	 */
	public Map<String, Object> getAllCheckInPersonInfo(Integer limit,Integer page) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<CheckInPerson> cipList = null;
		try {
			cipList = cPDaoImpl.queryCheckInPersonPage((page-1)*10, limit);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(CheckInPerson cip : cipList){
				Map<String, Object> cipMap = new HashMap<String, Object>();
				cipMap.put("check_in_person_id", cip.getCheck_in_person_id());
				cipMap.put("grogshop_order_id", cip.getGrogshop_order_id());
				cipMap.put("check_in_person_name", cip.getCheck_in_person_name());
				cipMap.put("check_in_person_ID_card", cip.getCheck_in_person_ID_card());
				list.add(cipMap);
			}
			map.put("data", list);
			map.put("count", Integer.valueOf(cPDaoImpl.queryCountCheckInPerson().toString()));
			map.put("msg", "");
			map.put("code", 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 将单个入住人员信息包装成map返回
	 */
	public Map<String, Object> getCheckInPersonInfoByIdCard(String check_in_person_ID_card){
		Map<String, Object> map = null;
		try {
			CheckInPerson person = cPDaoImpl.getCheckInPersonByIdCard(check_in_person_ID_card);
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
//	/**
//	 * 添加一组入住人信息
//	 * @param checkInPersonInfoMap
//	 * @return
//	 */
//	public String addCheckInPerson(
//			List<Map<String, Object>> checkInPersonInfoMap) {
//		for(Map<String, Object> checkInPersonMap : checkInPersonInfoMap){
//			CheckInPerson checkInPerson = new CheckInPerson();
//			
//		}
//		return null;
//	}
}

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
	public Map<String, Object> getAllCheckInPersonInfo(Integer cipPresentPage,Integer pageSize)throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//入住人员分页
		Long cpCount = cPDaoImpl.queryCountCheckInPerson();
		PageUtil<CheckInPerson> pu = new PageUtil<CheckInPerson>();
		pu.setCountRow(cpCount.intValue());
		pu.setCurrentPage(cipPresentPage);
		pu.setPageSize(pageSize);
		
		int cpStartRow = pu.getStartRow();
		int cpPageSize = pu.getPageSize();
		
		List<CheckInPerson> pageCIP = cPDaoImpl.queryCheckInPersonPage(cpStartRow, cpPageSize);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("cip", pageCIP);
		pu.setMap(dataMap);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageUtil", pu);
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

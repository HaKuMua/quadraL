package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zj.dao.ReserveDao;
import com.zj.dao.impl.ReserveDaoImpl;
import com.zj.entity.Reserve;
import com.zj.service.impl.ReserveServiceImpl;

public class ReserveService implements ReserveServiceImpl{
	private ReserveDaoImpl reserveDaoImpl = new ReserveDao();
	private Logger log = Logger.getLogger(ReserveService.class);
	@Override
	public List<Map<String, Object>> getAllReserve() {
		List<Map<String, Object>> list = null;
		try {
			List<Reserve> reserveList = reserveDaoImpl.getAllReserve();
			if(reserveList != null){
				list = new ArrayList<Map<String,Object>>();
				for(Reserve reserve : reserveList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("reserve_id", reserve.getReserve_id());
					map.put("reserve_date", reserve.getReserve_date());
					map.put("reserve_day_number", reserve.getReserve_day_number());
					map.put("check_out_date", reserve.getCheck_out_date());
					map.put("user_id", reserve.getUser_id());
					map.put("house_id", reserve.getHouse_id());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//	/**
//	 * 添加一个订单预定信息方法
//	 * @param reserve
//	 * @return
//	 */
//	public String addReserveInfo(Map<String, Object> reserveInfo) {
//		Reserve reserve = new Reserve();
//		try {
//			reserve.setReserve_date(new SimpleDateFormat("yyyy-MM-dd").parse(reserveInfo.get("reserve_date").toString()));
//			reserve.setReserve_day_number(Integer.valueOf(reserveInfo.get("reserve_day_number").toString()));
//			reserve.setCheck_out_date(new SimpleDateFormat("yyyy-MM-dd").parse(reserveInfo.get("check_out_date").toString()));
//			reserve.setUser_id(Integer.valueOf(reserveInfo.get("user_id").toString()));
//			reserve.setHouse_id(Integer.valueOf(reserveInfo.get("house_id").toString()));
//		} catch (ParseException e) {
//			log.error("Date型转换异常");
//		}
//		try {
//			if(reserveDaoImpl.addReserve(reserve)>0)
//				return "插入信息成功";
//			else
//				return "插入信息失败";
//		} catch (SQLException e) {
//			log.error("预订信息插入异常");
//			return "预订信息插入异常";
//		}
//	}
}

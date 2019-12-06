package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.util.PageUtil;

import com.zj.dao.HouseDao;
import com.zj.dao.ReserveDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.ReserveDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.Reserve;
import com.zj.entity.User;
import com.zj.service.impl.ReserveServiceImpl;

public class ReserveService implements ReserveServiceImpl{
	private ReserveDaoImpl reserveDaoImpl = new ReserveDao();
	private Logger log = Logger.getLogger(ReserveService.class);
	private UserDaoImpl  userDaoImpl = new UserDao(); 
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	/**
	 * 获得所有预订表信息
	 */
	@Override
	public Map<String, Object> getAllReserve(Integer limit,Integer page) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Reserve> reserveList = null;
		try {
			reserveList = reserveDaoImpl.queryReservePage((page-1)*10, limit);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(Reserve reserve : reserveList){
				Map<String, Object> orderMap = new HashMap<String, Object>();
					orderMap.put("reserve_id", reserve.getReserve_id());
					orderMap.put("reserve_date", reserve.getReserve_date());
					orderMap.put("reserve_day_number", reserve.getReserve_day_number());
					orderMap.put("check_out_date", reserve.getCheck_out_date());
					orderMap.put("user_name", userDaoImpl.getUserInfoById(reserve.getUser_id()).getUser_name());
					orderMap.put("house_name", houseDaoImpl.getHouseInfoByID(reserve.getHouse_id()).getHouse_name());
				list.add(orderMap);
			}
			map.put("data", list);
			map.put("count", Integer.valueOf(reserveDaoImpl.queryCountReserve().toString()));
			map.put("msg", "");
			map.put("code", 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 获得所有已预订时间
	 * @return
	 */
	public List<String> getReserveDate(Integer house_id) {
		List<String> dateList = new ArrayList<String>();
		try {
			List<Reserve> list = reserveDaoImpl.getReserveByHouseID(house_id);
			System.out.println("list:"+list);
			for(Reserve reserve : list){
				String date = reserve.getReserve_date()+"&"+reserve.getCheck_out_date();
				dateList.add(date);
			}
		} catch (SQLException e) {
			log.error("数据库操作异常");
		}
		return dateList;
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

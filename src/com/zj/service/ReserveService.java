package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.dao.ReserveDao;
import com.zj.dao.impl.ReserveDaoImpl;
import com.zj.entity.Reserve;
import com.zj.service.impl.ReserveServiceImpl;

public class ReserveService implements ReserveServiceImpl{
	private ReserveDaoImpl reserveDaoImpl = new ReserveDao();
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
}

package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.control.HouseControl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.HouseParticularsDaoImpl;
import com.zj.dao.realize.HouseDao;
import com.zj.dao.realize.HouseImgDao;
import com.zj.dao.realize.HouseParticularsDao;
import com.zj.entity.House;
import com.zj.entity.HouseImg;
import com.zj.entity.HouseParticulars;

/**
 * 
 * @author lijia
 *房子服务层类
 */
public class HouseService {
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private HouseParticularsDaoImpl houseParticularsDaoImpl = new HouseParticularsDao();
	/*
	 * 将所有房子信息包装成一个list<map>返回
	 */
	public List<Map<String, Object>> getAllHouseInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<House> allHouse = houseDaoImpl.getAllHouseInfo();
			List<HouseImg> allHouseImg = houseImgDaoImpl.getAllHouseImgInfo();
			List<HouseParticulars> allHouseParticulars = houseParticularsDaoImpl.getAllHouseParticularsInfo();
			if(allHouse != null){
				list = new ArrayList<Map<String,Object>>();
				for(int i = 0;i<allHouse.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("house_id", allHouse.get(i).getHouse_id());
					map.put("house_name", allHouse.get(i).getHouse_name());
					map.put("house_intake", allHouse.get(i).getHouse_intake());
					map.put("lease_type", allHouse.get(i).getLease_type());
					map.put("may_check_in_date", allHouse.get(i).getMay_check_in_date());
					map.put("may_check_out_date", allHouse.get(i).getMay_check_out_date());
					map.put("house_type", allHouse.get(i).getHouse_type());
					map.put("house_state", allHouse.get(i).getHouse_state());
					map.put("travel_information", allHouse.get(i).getTravel_information());
					map.put("house_price", allHouse.get(i).getHouse_price());
					map.put("house_address", allHouse.get(i).getHouse_address());
					map.put("landlord_id", allHouse.get(i).getLandlord_id());
					map.put("room_number", allHouseParticulars.get(i).getRoom_number());
					map.put("address_describe", allHouseParticulars.get(i).getAddress_describe());
					map.put("toilet_number", allHouseParticulars.get(i).getToilet_number());
					map.put("allHouseImg", allHouseImg);
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 将单个房子信息包装成map返回
	 */
	public Map<String, Object> getHouseInfoByID(Integer house_id){
		Map<String, Object> map = null;
		try {
			House house = houseDaoImpl.getHouseInfoByID(house_id);
			List<HouseImg> houseImgList = houseImgDaoImpl.getHouseImgByHouseID(house_id);
			HouseParticulars houseParticulars = houseParticularsDaoImpl.getHouseParticularsInfoByID(house.getHouse_particulars_id());
			if(house != null){
				map = new HashMap<String, Object>();
				map.put("house_id", house.getHouse_id());
				map.put("house_name", house.getHouse_name());
				map.put("house_intake", house.getHouse_intake());
				map.put("lease_type", house.getLease_type());
				map.put("may_check_in_date", house.getMay_check_in_date());
				map.put("may_check_out_date", house.getMay_check_out_date());
				map.put("house_type", house.getHouse_type());
				map.put("house_state", house.getHouse_state());
				map.put("travel_information", house.getTravel_information());
				map.put("house_price", house.getHouse_price());
				map.put("house_address", house.getHouse_address());
				map.put("landlord_id", house.getLandlord_id());
				map.put("room_number", houseParticulars.getRoom_number());
				map.put("address_describe", houseParticulars.getAddress_describe());
				map.put("toilet_number", houseParticulars.getToilet_number());
				map.put("allHouseImg", houseImgList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}

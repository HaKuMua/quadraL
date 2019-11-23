package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.control.HouseControl;
import com.zj.entity.House;
import com.zj.entity.HouseImg;
import com.zj.entity.HouseParticulars;

/**
 * 
 * @author lijia
 *房子服务层类
 */
public class HouseService {
	private HouseControl houseControl = new HouseControl();
	public List<Map<String, Object>> getAllHouseInfo(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			List<House> allHouse = houseControl.getAllHouseInfo();
			List<HouseImg> allHouseImg = houseControl.getAllHouseImgInfo();
			List<HouseParticulars> allHouseParticulars = houseControl.getAllHouseParticularsInfo();
			if(allHouse != null){
				for(House house : allHouse){
					Map<String, Object> map = new HashMap<String, Object>();
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
					for(HouseParticulars houseParticulars : allHouseParticulars){
						map.put("room_number", houseParticulars.getRoom_number());
						map.put("address_describe", houseParticulars.getAddress_describe());
						map.put("toilet_number", houseParticulars.getToilet_number());
					}
					map.put("allHouseImg", allHouseImg);
					list.add(map);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

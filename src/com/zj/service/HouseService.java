package com.zj.service;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zj.dao.HouseCommentDao;
import com.zj.dao.HouseDao;
import com.zj.dao.HouseImgDao;
import com.zj.dao.HouseParticularsDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.HouseParticularsDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.House;
import com.zj.entity.HouseComment;
import com.zj.entity.HouseImg;
import com.zj.entity.HouseParticulars;
import com.zj.service.impl.HouseServiceImpl;

/**
 * 
 * @author lijia
 *房子服务层类
 */
public class HouseService implements HouseServiceImpl{
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private HouseParticularsDaoImpl houseParticularsDaoImpl = new HouseParticularsDao();
	private HouseCommentDaoImpl houseCommentDaoImpl = new HouseCommentDao();
	private UserDaoImpl userDao = new UserDao();
	private Logger log = Logger.getLogger(HouseService.class);
	/**
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
					//通过房子id获取房子图片路径
					Integer house_id = allHouse.get(i).getHouse_id();
					HouseImg hImg =  (HouseImg) houseImgDaoImpl.getHouseImgByHouseID(house_id);
					map.put("house_img_url", hImg.getHouse_img_url());
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
					map.put("user_id", allHouse.get(i).getUser_id());
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
	/**
	 * 将单个房子信息包装成map返回
	 */
	public Map<String, Object> getHouseInfoByID(Integer house_id){
		Map<String, Object> map = null;
		try {
			House house = houseDaoImpl.getHouseInfoByID(house_id);
			List<HouseImg> houseImgList = houseImgDaoImpl.getHouseImgByHouseID(house_id);
			HouseParticulars houseParticulars = houseParticularsDaoImpl.getHouseParticularsInfoByID(house.getHouse_particulars_id());
			List<HouseComment> houseCommentList = houseCommentDaoImpl.getHouseCommentByHouseID(house_id);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(HouseComment houseComment : houseCommentList){
				Map<String, Object> commentMap = new HashMap<String, Object>();
				commentMap.put("houseCom_content", houseComment.getHouseCom_content());
				commentMap.put("houseCom_date", houseComment.getHouseCom_date());
				if(houseComment.getReplier_id() != null) {
					commentMap.put("replier_name", userDao.getUserInfoByID(houseComment.getReplier_id()).getUser_name());
				}else {
					commentMap.put("replier_name",null);
				}
				
				commentMap.put("user_name", userDao.getUserInfoByID(houseComment.getUser_id()).getUser_name());
				commentMap.put("user_headimg_url", userDao.getUserInfoByID(houseComment.getUser_id()).getUser_headimg_url());
				list.add(commentMap);
			}
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
				map.put("User_id", house.getUser_id());
				map.put("room_number", houseParticulars.getRoom_number());
				map.put("address_describe", houseParticulars.getAddress_describe());
				map.put("toilet_number", houseParticulars.getToilet_number());
				map.put("allHouseImg", houseImgList);
				map.put("allHouseComment", list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加一个房子信息方法
	 * @param house 一个房子信息
	 * @return
	 * @throws SQLException
	 */
	public String addHouseInfo(Map<String, Object> houseInfo) {
		House house = new House();
		HouseParticulars houseParticulars = new HouseParticulars();
		//给房子实体类set值进去
		if(houseInfo.get("landlord_id") != null)
			house.setUser_id(Integer.valueOf(houseInfo.get("User_id").toString()));
		if(houseInfo.get("house_name") != null)
			house.setHouse_name(houseInfo.get("house_name").toString());
		if(houseInfo.get("house_intake") != null)
			house.setHouse_intake(Integer.valueOf(houseInfo.get("house_intake").toString()));
		if(houseInfo.get("lease_type") != null)
			house.setLease_type(houseInfo.get("lease_type").toString());
		if(houseInfo.get("may_check_in_date") != null)
			house.setMay_check_in_date(Time.valueOf(houseInfo.get("may_check_in_date").toString()));
		if(houseInfo.get("may_check_out_date") != null)
			house.setMay_check_out_date(Time.valueOf(houseInfo.get("may_check_out_date").toString()));
		if(houseInfo.get("house_type") != null)
			house.setHouse_type(houseInfo.get("house_type").toString());
		if(houseInfo.get("house_particulars_id") != null)
			house.setHouse_particulars_id(Integer.valueOf(houseInfo.get("house_particulars_id").toString()));
		if(houseInfo.get("house_state") != null)
			house.setHouse_state(Integer.valueOf(houseInfo.get("house_state").toString()));
		if(houseInfo.get("travel_information") != null)
			house.setTravel_information(houseInfo.get("travel_information").toString());
		if(houseInfo.get("house_price") != null)
			house.setHouse_price(Double.valueOf(houseInfo.get("house_price").toString()));
		if(houseInfo.get("house_address") != null)
			house.setHouse_address(houseInfo.get("house_address").toString());
		
		//给房子详情实体类设置值进去
		if(houseInfo.get("room_number") != null)
			houseParticulars.setRoom_number(Integer.valueOf(houseInfo.get("room_number").toString()));
		if(houseInfo.get("address_describe") != null)
			houseParticulars.setAddress_describe(houseInfo.get("address_describe").toString());
		if(houseInfo.get("toilet_number") != null)
			houseParticulars.setToilet_number(Integer.valueOf(houseInfo.get("toilet_number").toString()));
		if(houseInfo.get("house_describe") != null)
			houseParticulars.setHouse_describe(houseInfo.get("house_describe").toString());
		try {
			if(houseDaoImpl.addHouseInfo(house)>0 && houseParticularsDaoImpl.addHouseParticularsInfo(houseParticulars)>0)
				return "房子信息插入成功！";
		} catch (SQLException e) {
			log.error("插入异常！");
		}
		return "房子信息插入失败！";
	}
	/**
	 * 添加一组图片方法
	 */
	public String addHouseImg(List<Map<String, Object>> houseImgList) {
		for(Map<String, Object> map : houseImgList){
			HouseImg houseImg = new HouseImg();
			houseImg.setHouse_id(Integer.valueOf(map.get("house_id").toString()));
			houseImg.setHouse_img_url(map.get("house_img_url").toString());
			try {
				if(houseImgDaoImpl.addHouseImgInfo(houseImg) > 0){
					log.info("图片"+map.get("house_img_id")+"插入成功！");
				}
			} catch (SQLException e) {
				log.error("插入异常！");
				return "图片"+map.get("house_img_id")+"插入失败！";
			}
		}
		return "插入成功！";
	}
	
}

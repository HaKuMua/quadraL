package com.zj.service;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zj.dao.BuildingCodesDao;
import cn.com.util.PageUtil;
import com.zj.dao.HouseCommentDao;
import com.zj.dao.HouseDao;
import com.zj.dao.HouseImgDao;
import com.zj.dao.HouseParticularsDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.BuildingCodesDaoImpl;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.HouseParticularsDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.BuildingCodes;
import com.zj.entity.CheckInPerson;
import com.zj.entity.Facilities;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.House;
import com.zj.entity.HouseComment;
import com.zj.entity.HouseImg;
import com.zj.entity.HouseParticulars;
import com.zj.entity.User;
import com.zj.service.impl.HouseServiceImpl;

/**
 * 
 * @author lijia 房子服务层类
 */
public class HouseService implements HouseServiceImpl {
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private HouseParticularsDaoImpl houseParticularsDaoImpl = new HouseParticularsDao();
	private HouseCommentDaoImpl houseCommentDaoImpl = new HouseCommentDao();
	private BuildingCodesDaoImpl buildingCodesDaoImpl = new BuildingCodesDao();
	private UserDaoImpl userDaoImpl = new UserDao();
	private Logger log = Logger.getLogger(HouseService.class);

	/**
	 * 将所有房子信息包装成一个list<map>返回
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllHouseInfo(Integer limit,Integer page) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		List<House> houseList = null;
		try {
			houseList = houseDaoImpl.queryHousePage((page-1)*10, limit);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(House house : houseList){
				Map<String, Object> houseMap = new HashMap<String, Object>();
					houseMap.put("house_id", house.getHouse_id());
					houseMap.put("user_name",userDaoImpl.getUserInfoById(house.getUser_id()).getUser_name() );
					houseMap.put("house_name", house.getHouse_name());
					houseMap.put("house_intake", house.getHouse_intake());
					houseMap.put("lease_type", house.getLease_type());
					houseMap.put("may_check_in_date", house.getMay_check_in_date());
					houseMap.put("may_check_out_date", house.getMay_check_out_date());
					houseMap.put("house_type", house.getHouse_type());
					if(house.getHouse_state() == 0)
						houseMap.put("house_state", "未审核");
					else
						houseMap.put("house_state", "已审核");
					houseMap.put("house_price", house.getHouse_price());
					houseMap.put("travel_information", house.getTravel_information());
					houseMap.put("house_address", house.getHouse_address());
				
				list.add(houseMap);
			}
			map.put("data", list);
			map.put("count", Integer.valueOf(houseDaoImpl.queryCountHouse().toString()));
			map.put("msg", "");
			map.put("code", 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	}

	/**
	 * 将单个房子信息包装成map返回
	 */
	public Map<String, Object> getHouseInfoByID(Integer house_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			House house = houseDaoImpl.getHouseInfoByID(house_id);
			List<HouseImg> houseImgList = houseImgDaoImpl
					.getHouseImgByHouseID(house_id);
			HouseParticulars houseParticulars = houseParticularsDaoImpl
					.getHouseParticularsInfoByID(house
							.getHouse_particulars_id());
			List<BuildingCodes> houseBuilding_code = buildingCodesDaoImpl
					.queryOneHouseBuildingCodes(house_id);
			System.out.println(houseBuilding_code);
			List<String> houseBuildingList = new ArrayList<String>();
			for (BuildingCodes houseBu : houseBuilding_code) {
				System.out.println(houseBu.getBuilding_name());
				houseBuildingList.add(houseBu.getBuilding_name());
			}
			if(!houseBuildingList.isEmpty()) {
				map.put("building_name", houseBuildingList);
			}
			List<Facilities> houseFacilities = buildingCodesDaoImpl
					.queryFacilities(house_id);
			List<String> houseFacilitiesList = new ArrayList<String>();
			for (Facilities facilities : houseFacilities) {
				houseFacilitiesList.add(facilities.getFacilities_name());
			}
			map.put("facilities_name", houseFacilitiesList);
			List<HouseComment> houseCommentList = houseCommentDaoImpl
					.getHouseCommentByHouseID(house_id);
			List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
			for (HouseComment houseComment : houseCommentList) {
				Map<String, Object> commentMap = new HashMap<String, Object>();
				commentMap.put("houseCom_content",
						houseComment.getHouseCom_content());
				commentMap
						.put("houseCom_date", houseComment.getHouseCom_date());
				if (houseComment.getReplier_id() != null) {
					commentMap.put(
							"replier_name",
							userDaoImpl.getUserInfoById(
									houseComment.getReplier_id())
									.getUser_name());
				} else {
					commentMap.put("replier_name", null);
				}

				commentMap.put("user_name",
						userDaoImpl.getUserInfoById(houseComment.getUser_id())
								.getUser_name());
				commentMap.put("user_headimg_url",
						userDaoImpl.getUserInfoById(houseComment.getUser_id())
								.getUser_headimg_url());
				commentList.add(commentMap);
			}
			if (house != null) {
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
				map.put("user_id", house.getUser_id());
				User user = userDaoImpl.getUserInfoById( house.getUser_id());
				map.put("user_name", user.getUser_name());
				map.put("user_img", user.getUser_headimg_url());
				map.put("user_describe", user.getUser_describe());
				map.put("room_number", houseParticulars.getRoom_number());
				map.put("address_describe",
						houseParticulars.getAddress_describe());
				map.put("house_describe",
						houseParticulars.getHouse_describe());
				map.put("toilet_number", houseParticulars.getToilet_number());
				map.put("allHouseImg", houseImgList);
				map.put("allHouseComment", commentList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 添加一个房子信息方法
	 * 
	 * @param house
	 *            一个房子信息
	 * @return
	 * @throws SQLException
	 */
	public Integer addHouseInfo(Map<String, Object> houseInfo) {
		House house = new House();
		HouseParticulars houseParticulars = new HouseParticulars();
		// 给房子实体类set值进去
		if (houseInfo.get("user_id") != null)
			house.setUser_id(Integer.valueOf(houseInfo.get("user_id")
					.toString()));
		if (houseInfo.get("house_name") != null)
			house.setHouse_name(houseInfo.get("house_name").toString());
		if (houseInfo.get("house_intake") != null)
			house.setHouse_intake(Integer.valueOf(houseInfo.get("house_intake")
					.toString()));
		if (houseInfo.get("lease_type") != null)
			house.setLease_type(houseInfo.get("lease_type").toString());
		if (houseInfo.get("may_check_in_date") != null)
			house.setMay_check_in_date(houseInfo.get("may_check_in_date")
					.toString());
		if (houseInfo.get("may_check_out_date") != null)
			house.setMay_check_out_date(houseInfo.get("may_check_out_date")
					.toString());
		if (houseInfo.get("house_type") != null)
			house.setHouse_type(houseInfo.get("house_type").toString());
		if (houseInfo.get("house_particulars_id") != null)
			house.setHouse_particulars_id(Integer.valueOf(houseInfo.get(
					"house_particulars_id").toString()));
		if (houseInfo.get("travel_information") != null)
			house.setTravel_information(houseInfo.get("travel_information")
					.toString());
		if (houseInfo.get("house_price") != null)
			house.setHouse_price(Double.valueOf(houseInfo.get("house_price")
					.toString()));
		if (houseInfo.get("house_address") != null)
			house.setHouse_address(houseInfo.get("house_address").toString());
		if (houseInfo.get("location_id") != null)
			house.setLocation_id(houseInfo.get("location_id").toString());
		// 给房子详情实体类设置值进去
		if (houseInfo.get("room_number") != null)
			houseParticulars.setRoom_number(Integer.valueOf(houseInfo.get(
					"room_number").toString()));
		if (houseInfo.get("address_describe") != null)
			houseParticulars.setAddress_describe(houseInfo.get(
					"address_describe").toString());
		if (houseInfo.get("toilet_number") != null)
			houseParticulars.setToilet_number(Integer.valueOf(houseInfo.get(
					"toilet_number").toString()));
		if (houseInfo.get("house_describe") != null)
			houseParticulars.setHouse_describe(houseInfo.get("house_describe")
					.toString());
		Integer house_id;
		int count = -1;

		try {
			//改变用户is_landlord状态 1 为是房东
			count =userDaoImpl.becomeLandlord(house.getUser_id());
			// 插房子详情
			count = houseParticularsDaoImpl
					.addHouseParticularsInfo(houseParticulars);
			if (count > 0) {
				house.setHouse_particulars_id(houseParticularsDaoImpl
						.getHouseParticularsIdByInfo(houseParticulars));
				// 插房子
				count = houseDaoImpl.addHouseInfo(house);
				if (count > 0) {
					house_id = houseDaoImpl.getHouseByName(house
							.getHouse_name());
					System.out.println(house_id);
					// 给房子守则设置值
					String[] building = houseInfo.get("building_name")
							.toString().split(",");
					for (int i = 0; i < building.length; i++) {
						count = buildingCodesDaoImpl.addBuildingCodes(house_id,
								building[i]);
					}
					if (count > 0) {
						// 设置房子设施
						String[] facilities = houseInfo.get("facilities_name")
								.toString().split(",");
						for (int i = 0; i < facilities.length; i++) {
							count = buildingCodesDaoImpl.addFacilities(
									house_id, facilities[i]);
						}
					}else {
						log.error("房子信息插入异常！");
						return -1;
					}
				}else {
					log.error("房子信息插入异常！");
					return -1;
				}
			}else {
				log.error("房子信息插入异常！");
				return -1;
			}
		} catch (SQLException e) {
			log.error("房子信息插入异常！");
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	/**
	 * 添加一组图片方法
	 */
	public Integer addHouseImg(String house_name, String houseImgList) {
		String[] images = houseImgList.split(",");
		for (int i = 0; i < images.length; i++) {
			HouseImg houseImg = new HouseImg();
			try {
				Integer house_id = houseDaoImpl.getHouseByName(house_name);
				houseImg.setHouse_id(house_id);
				houseImg.setHouse_img_url(images[i]);
				System.out.println(house_id);
				if (houseImgDaoImpl.addHouseImgInfo(houseImg) > 0) {
					log.info("图片插入成功！");
				}
			} catch (Exception e) {
				log.error("图片插入异常！");
				return -1;
			}
		}
		return 1;
	}

	/**
	 * 通过用户ID获取此用户旗下所有房子信息
	 * 
	 * @param user_id
	 * @return
	 */
	public List<Map<String, Object>> getHouseByID(Integer user_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<House> houseList = houseDaoImpl.getHouseByID(user_id);
			for (House house : houseList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("house_id", house.getHouse_id());
				List<HouseImg> houseimgs = houseImgDaoImpl.getHouseImgByHouseID(house.getHouse_id());
				map.put("house_img",houseimgs.get(0).getHouse_img_url() );
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
				map.put("location_id", house.getLocation_id());
				list.add(map);
			}
		} catch (SQLException e) {
			log.error("数据库查询异常");
		}
		return list;
	}

	/**
	 * 通过筛选返回指定房子信息
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getHouseByDateOrAddress(
			String reserve_date, String check_out_date, String house_address) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<House> houseList = null;
			if (house_address == null || house_address == "") {
				houseList = houseDaoImpl.getHouseByDate(new SimpleDateFormat(
						"yyyy-MM-dd").parse(reserve_date),
						new SimpleDateFormat("yyyy-MM-dd")
								.parse(check_out_date));
			} else if (reserve_date == null || reserve_date.equals("-1") || check_out_date == null ||check_out_date == "-1") {
				houseList = houseDaoImpl.getHouseByAdd(house_address);
			} else {
				houseList = houseDaoImpl.getHouseByDateAndAdd(
						new SimpleDateFormat("yyyy-MM-dd").parse(reserve_date),
						new SimpleDateFormat("yyyy-MM-dd")
								.parse(check_out_date), house_address);
			}
			for (House house : houseList) {
				Map<String, Object> map = new HashMap<String, Object>();
				HouseParticulars houseParticulars = houseParticularsDaoImpl
						.getHouseParticularsInfoByID(house
								.getHouse_particulars_id());
				map.put("house_id", house.getHouse_id());
				List<HouseImg> houseimgs = houseImgDaoImpl.getHouseImgByHouseID(house.getHouse_id());
				map.put("house_img",houseimgs );
				map.put("user_id", house.getUser_id());
				User user = userDaoImpl.getUserInfoById( house.getUser_id());
				map.put("user_name", user.getUser_name());
				map.put("user_img", user.getUser_headimg_url());
				map.put("house_name", house.getHouse_name());
				map.put("room_number", houseParticulars.getRoom_number());
				map.put("toilet_number", houseParticulars.getToilet_number());
				map.put("house_intake", house.getHouse_intake());
				map.put("lease_type", house.getLease_type());
				map.put("may_check_in_date", house.getMay_check_in_date());
				map.put("may_check_out_date", house.getMay_check_out_date());
				map.put("house_type", house.getHouse_type());
				map.put("house_state", house.getHouse_state());
				map.put("travel_information", house.getTravel_information());
				map.put("house_price", house.getHouse_price());
				map.put("house_address", house.getHouse_address());
				map.put("location_id", house.getLocation_id());
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("数据库查询异常");
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("时间类型转换异常");
		}
		return list;
	}

}

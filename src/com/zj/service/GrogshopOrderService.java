package com.zj.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.util.PageUtil;
import cn.com.util.UUIDGenerator;

import com.zj.dao.CheckInPersonDao;
import com.zj.dao.GrogshopOrderDao;
import com.zj.dao.HouseDao;
import com.zj.dao.HouseImgDao;
import com.zj.dao.NoticeDao;
import com.zj.dao.ReserveDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.CheckInPersonDaoImpl;
import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.NoticeDaoImpl;
import com.zj.dao.impl.ReserveDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.CheckInPerson;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.House;
import com.zj.entity.HouseImg;
import com.zj.entity.Reserve;
import com.zj.entity.User;
import com.zj.service.impl.GrogshopOrderServiceImpl;

/**
 * 订单服务层
 * 
 * @author LanceEdward
 * 
 */
public class GrogshopOrderService implements GrogshopOrderServiceImpl {
	private GrogshopOrderDaoImpl orderDaoImpl = new GrogshopOrderDao();
	private ReserveDaoImpl reserveDaoImpl = new ReserveDao();
	private CheckInPersonDaoImpl checkInPersonDaoImpl = new CheckInPersonDao();
	private UserDaoImpl userDaoImpl = new UserDao();
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private NoticeDaoImpl noticeDaoImpl = new NoticeDao();
	private Logger log = Logger.getLogger(GrogshopOrderService.class);

	// 订单当前页
	/**
	 * 将所有订单信息包装成一个list<map>返回 分页显示订单信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getAllGrogshopOrderInfo(Integer limit,
			Integer page) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<GrogshopOrder> orderList = null;
		try {
			orderList = orderDaoImpl.queryOrderPage((page - 1) * 10, limit);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (GrogshopOrder order : orderList) {
				Map<String, Object> orderMap = new HashMap<String, Object>();
				orderMap.put("grogshop_order_id", order.getGrogshop_order_id());
				orderMap.put("user_name",
						userDaoImpl.getUserInfoById(order.getUser_id())
								.getUser_name());
				orderMap.put("price", order.getPrice());
				orderMap.put("place_an_order_date",
						order.getPlace_an_order_date());
				if (order.getGrogshop_order_state().equals("1"))
					orderMap.put("grogshop_order_state", "已支付");
				else if (order.getGrogshop_order_state().equals("2"))
					orderMap.put("grogshop_order_state", "已处理");
				else if (order.getGrogshop_order_state().equals("3"))
					orderMap.put("grogshop_order_state", "已完成");
				orderMap.put("grogshop_order_describe",
						order.getGrogshop_order_describe());
				orderMap.put("reserve_id", order.getReserve_id());
				list.add(orderMap);
			}
			map.put("data", list);
			map.put("count",
					Integer.valueOf(orderDaoImpl.queryCountOrder().toString()));
			map.put("msg", "");
			map.put("code", 0);
		} catch (SQLException e) {
			log.error("数据库查询错误！");
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 将单个订单信息包装成map返回
	 */
	public Map<String, Object> getAllGrogshopOrderInfoByID(
			String grogshop_order_id) {
		Map<String, Object> map = null;
		try {
			GrogshopOrder order = orderDaoImpl
					.getGrogshopOrderInfoByID(grogshop_order_id);
			if (order != null) {
				map = new HashMap<String, Object>();
				map.put("grogshop_order_id", order.getGrogshop_order_id());
				map.put("user_id", order.getUser_id());
				map.put("price", order.getPrice());
				if (order.getGrogshop_order_state().equals("1"))
					map.put("grogshop_order_state", "已支付");
				else
					map.put("grogshop_order_state", "已完成");
				map.put("grogshop_order_state", order.getGrogshop_order_state());
				map.put("grogshop_order_describe",
						order.getGrogshop_order_describe());
				map.put("reserve_id", order.getReserve_id());
			}
		} catch (SQLException e) {
			log.error("数据库操作异常");
		}
		return map;
	}

	/**
	 * 添加一个订单信息方法
	 * 
	 * @param grogshopOrder
	 * @return
	 */
	public Integer addGrogshopOrderInfo(Map<String, Object> grogshopOrderInfo,
			List<Map<String, Object>> checkInPersonInfoMap) {
		try {
			// 下单先扣钱
			Integer user_id = Integer.valueOf(grogshopOrderInfo.get("user_id")
					.toString());
			Double price = Double.valueOf(grogshopOrderInfo.get("price")
					.toString());
			User user = userDaoImpl.getUserInfoById(user_id);
			if (price > user.getMoney())
				return -1;
			if (userDaoImpl.updateUserMoney(price, user_id) > 0)
				log.debug("扣钱成功");
			// 插入预定表
			Reserve reserve = new Reserve();
			reserve.setReserve_date(new SimpleDateFormat("yyyy-MM-dd")
					.parse(grogshopOrderInfo.get("reserve_date").toString()));
			reserve.setReserve_day_number(Integer.valueOf(grogshopOrderInfo
					.get("reserve_day_number").toString()));
			reserve.setCheck_out_date(new SimpleDateFormat("yyyy-MM-dd")
					.parse(grogshopOrderInfo.get("check_out_date").toString()));
			reserve.setUser_id(user_id);
			reserve.setHouse_id(Integer.valueOf(grogshopOrderInfo.get(
					"house_id").toString()));
			if (reserveDaoImpl.addReserve(reserve) > 0) {
				log.debug("插入信息成功");
			} else {
				log.debug("插入信息失败");
				return -2;
			}
			// 插入订单表
			GrogshopOrder grogshopOrder = new GrogshopOrder();
			String uuID = UUIDGenerator.getUUID();
			grogshopOrder.setGrogshop_order_id(uuID);
			grogshopOrder.setUser_id(Integer.valueOf(grogshopOrderInfo.get(
					"user_id").toString()));
			grogshopOrder.setPrice(price);
			if (grogshopOrderInfo.get("grogshop_order_describe") != null)
				grogshopOrder.setGrogshop_order_describe(grogshopOrderInfo.get(
						"grogshop_order_describe").toString());
			Integer reserve_id = reserveDaoImpl.getReserveID(reserve);
			grogshopOrder.setReserve_id(reserve_id);
			log.debug(grogshopOrder);
			if (orderDaoImpl.addGrogshopOrderInfo(grogshopOrder) > 0) {
				log.debug("订单信息插入成功");
			} else {
				log.debug("订单信息插入失败");
				return -2;
			}
			// 插入通知
			noticeDaoImpl.addNotice("有用户在您这预定了房子", Integer
					.valueOf(grogshopOrderInfo.get("landlord").toString()));
			// 插入入住人表
			for (Map<String, Object> checkInPersonMap : checkInPersonInfoMap) {
				CheckInPerson checkInPerson = new CheckInPerson();
				checkInPerson.setGrogshop_order_id(uuID);
				checkInPerson.setCheck_in_person_name(checkInPersonMap.get(
						"name").toString());
				checkInPerson.setCheck_in_person_ID_card(checkInPersonMap.get(
						"idCard").toString());
				if (checkInPersonDaoImpl.addCheckInPerson(checkInPerson) > 0) {
					log.debug("插入入住人信息成功");
				} else {
					log.debug("插入入住人信息失败");
					return -2;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			log.error("数据库操作异常");
			return -2;
		} catch (ParseException e) {
			log.error("Date型转换异常");
			return -2;
		}
		return 1;
	}

	/**
	 * 通过用户ID获取此用户的所有订单信息
	 */
	public List<Map<String, Object>> getGrogshopOrderInfoByUserID(
			Integer user_id,Integer state) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<GrogshopOrder> orderList = null;
		try {
			if(state.equals(1))
				orderList = orderDaoImpl.getGrogshopOrderInfoByUserID(user_id);
			else if(state.equals(3))
				orderList = orderDaoImpl.getFinishOrderInfoByUserID(user_id);
			else 
				orderList = orderDaoImpl.getNotOrderInfoByUserID(user_id);
				
			for (GrogshopOrder order : orderList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("order_id", order.getGrogshop_order_id());
				map.put("price", order.getPrice());
				map.put("grogshop_order_state", order.getGrogshop_order_state());
				map.put("landlordState", order.getGrogshop_order_landlordState());
				Reserve reserve = reserveDaoImpl.getReserveInfoByID(order
						.getReserve_id());
				map.put("reserve_date", reserve.getReserve_date());
				map.put("reserve_day_number", reserve.getReserve_day_number());
				map.put("check_out_date", reserve.getCheck_out_date());
				House house = houseDaoImpl.getHouseInfoByID(reserve
						.getHouse_id());
				map.put("house_id", house.getHouse_id());
				map.put("landlord_id", house.getUser_id());
				map.put("house_name", house.getHouse_name());
				map.put("locationId", house.getLocation_id());
				map.put("house_address", house.getHouse_address());
				HouseImg houseImg = houseImgDaoImpl.getHouseImgByHouseID(
						reserve.getHouse_id()).get(0);
				map.put("house_img_url", houseImg.getHouse_img_url());
				list.add(map);
			}
		} catch (SQLException e) {
			log.error("数据库查询异常");
		}
		return list;
	}

	/**
	 * 通过房东ID获取所有在此房东的房子中下单的用户订单信息
	 * 
	 * @param user_id
	 * @return
	 */
	public Map<String, Object> getGrogshopOrderInfoByLandlordID(
			Integer user_id, Integer state, Integer limit, Integer page) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<GrogshopOrder> orderList = orderDaoImpl.getOrderByHouseUserId(
					user_id, state, (page - 1) * 10, limit);
			for (GrogshopOrder order : orderList) {
				Map<String, Object> map = new HashMap<String, Object>();
				Reserve reserve = reserveDaoImpl.getReserveInfoByID(order
						.getReserve_id());
				map.put("reserve_date", reserve.getReserve_date());
				map.put("reserve_day_number", reserve.getReserve_day_number());
				map.put("check_out_date", reserve.getCheck_out_date());
				map.put("house_name", (houseDaoImpl.getHouseByHouseId(reserve
						.getHouse_id())).getHouse_name());
				map.put("house_id", (reserve.getHouse_id()));
				map.put("price", order.getPrice());
				if (order.getGrogshop_order_landlordState().equals("1"))
					map.put("grogshop_order_state", "未处理");
				else if (order.getGrogshop_order_landlordState().equals("2"))
					map.put("grogshop_order_state", "已处理");
				else if (order.getGrogshop_order_landlordState().equals("3"))
					map.put("grogshop_order_state", "已完成");
				map.put("grogshop_order_id", order.getGrogshop_order_id());
				User user = userDaoImpl.getUserInfoById(order.getUser_id());
				map.put("user_name", user.getUser_name());
				String real_name = user.getReal_name();
				if (real_name != null)
					map.put("real_name", user.getReal_name());
				else
					map.put("real_name", "未实名");
				map.put("user_phone", user.getUser_phone());
				map.put("user_headimg_url", user.getUser_headimg_url());
				List<Map<String, Object>> checkinperson = new ArrayList<Map<String, Object>>();
				List<CheckInPerson> checkin = new ArrayList<CheckInPerson>();
				checkin = orderDaoImpl.getPersonByOrderId(order
						.getGrogshop_order_id());
				for (CheckInPerson check : checkin) {
					Map<String, Object> personMap = new HashMap<String, Object>();
					personMap.put("name", check.getCheck_in_person_name());
					personMap.put("IDcard", check.getCheck_in_person_ID_card());
					checkinperson.add(personMap);
				}
				map.put("checkinperson", checkinperson);
				list.add(map);
			}
			orderMap.put("data", list);
			orderMap.put(
					"count",
					Integer.valueOf(orderDaoImpl.getOrderNumByState(user_id,
							state).toString()));
			orderMap.put("msg", "");
			orderMap.put("code", 0);
		} catch (SQLException e) {
			log.error("数据库查询异常");
		}
		return orderMap;
	}

	/**
	 * 处理订单，更新信息
	 * 
	 * @param order_id
	 *            ,status
	 * @return
	 */
	public Integer updateOrder(String order_id, Integer state) {
		Integer code = -1;
		try {
			GrogshopOrder order = orderDaoImpl
					.getGrogshopOrderInfoByID(order_id);
			Integer count = 0;
			if (state.equals(2)) {
				System.out.println("房东确认订单！");
				count = orderDaoImpl.updateLandlordStatus(order_id, state);
				System.out.println(count);
				// 插入通知
				count = noticeDaoImpl.addNotice("房东已确认您的订单！",
						order.getUser_id());
				if (count > 0) {
					code = 1;
					return code;
				}
			} else if (state.equals(3)) {
				System.out.println("房东取消订单！");
				count = orderDaoImpl.updateLandlordStatus(order_id, state);
				// 插入通知
				count = noticeDaoImpl.addNotice("房东已取消您的订单！",
						order.getUser_id());
				if (count > 0) {
					code = 1;
					return code;
				}
			}
		} catch (SQLException e) {
			log.error("数据库查询错误");
			e.printStackTrace();
		}
		return code;
	}
}

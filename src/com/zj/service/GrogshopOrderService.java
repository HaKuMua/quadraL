package com.zj.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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
 * @author LanceEdward
 *
 */
public class GrogshopOrderService implements GrogshopOrderServiceImpl{
	private GrogshopOrderDaoImpl orderDaoImpl = new GrogshopOrderDao();
	private ReserveDaoImpl reserveDaoImpl = new ReserveDao();
	private CheckInPersonDaoImpl checkInPersonDaoImpl = new CheckInPersonDao();
	private UserDaoImpl userDaoImpl = new UserDao();
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private NoticeDaoImpl noticeDaoImpl = new NoticeDao();
	private Logger log = Logger.getLogger(GrogshopOrderService.class);
	/**
	 * 将所有订单信息包装成一个list<map>返回 
	 * @return
	 */
	public List<Map<String, Object>> getAllGrogshopOrderInfo(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			List<GrogshopOrder> allOrder = orderDaoImpl.getAllGrogshopOrderInfo();
			if(allOrder != null){
				list = new ArrayList<Map<String,Object>>();
				for(int i = 0;i<allOrder.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("grogshop_order_id", allOrder.get(i).getGrogshop_order_id());
					map.put("user_id", allOrder.get(i).getUser_id());
					Integer user_id = allOrder.get(i).getUser_id();
					User user = userDaoImpl.getUserInfoById(user_id);
					map.put("user_name", user.getUser_name());
					map.put("price", allOrder.get(i).getPrice());
					map.put("place_an_order_date", allOrder.get(i).getPlace_an_order_date());
					map.put("grogshop_order_state", allOrder.get(i).getGrogshop_order_state());
					map.put("grogshop_order_describe", allOrder.get(i).getGrogshop_order_describe());
					map.put("reserve_id", allOrder.get(i).getReserve_id());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 将单个订单信息包装成map返回
	 */
	public Map<String, Object> getAllGrogshopOrderInfoByID(Integer grogshop_order_id){
		Map<String, Object> map = null;
		try {
			GrogshopOrder order = orderDaoImpl.getGrogshopOrderInfoByID(grogshop_order_id);
			if(order != null){
				map = new HashMap<String, Object>();
				map.put("grogshop_order_id", order.getGrogshop_order_id());
				map.put("user_id", order.getUser_id());
				map.put("price", order.getPrice());
				map.put("place_an_order_date", order.getGrogshop_order_state());
				map.put("grogshop_order_state", order.getGrogshop_order_state());
				map.put("grogshop_order_describe", order.getGrogshop_order_describe());
				map.put("reserve_id", order.getReserve_id());
			}
		} catch (SQLException e) {
			log.error("数据库操作异常");
		}
		return map;
	}
	/**
	 * 添加一个订单信息方法
	 * @param grogshopOrder
	 * @return
	 */
	public String addGrogshopOrderInfo(Map<String, Object> grogshopOrderInfo,
			List<Map<String, Object>> checkInPersonInfoMap) {
		try {
			//下单先扣钱
			Integer user_id = Integer.valueOf(grogshopOrderInfo.get("user_id").toString());
			Double price = Double.valueOf(grogshopOrderInfo.get("price").toString());
			User user = userDaoImpl.getUserInfoById(user_id);
			if(price>user.getMoney())
				return "余额不足下单失败";
			if(userDaoImpl.updateUserMoney(price, user_id) > 0)
				log.debug("扣钱成功");
			//插入预定表
			Reserve reserve = new Reserve();
			reserve.setReserve_date(new SimpleDateFormat("yyyy-MM-dd").parse(grogshopOrderInfo.get("reserve_date").toString()));
			reserve.setReserve_day_number(Integer.valueOf(grogshopOrderInfo.get("reserve_day_number").toString()));
			reserve.setCheck_out_date(new SimpleDateFormat("yyyy-MM-dd").parse(grogshopOrderInfo.get("check_out_date").toString()));
			reserve.setUser_id(user_id);
			reserve.setHouse_id(Integer.valueOf(grogshopOrderInfo.get("house_id").toString()));
			if(reserveDaoImpl.addReserve(reserve)>0){
				log.debug("插入信息成功");
			}else{
				log.debug("插入信息失败");
				return null;
			}
			//插入订单表
			GrogshopOrder grogshopOrder = new GrogshopOrder();
			String uuID = UUIDGenerator.getUUID();
			grogshopOrder.setGrogshop_order_id(uuID);
			grogshopOrder.setUser_id(Integer.valueOf(grogshopOrderInfo.get("user_id").toString()));
			grogshopOrder.setPrice(price);
			grogshopOrder.setGrogshop_order_state(grogshopOrderInfo.get("grogshop_order_state").toString());
			if(grogshopOrderInfo.get("grogshop_order_describe") !=null)
				grogshopOrder.setGrogshop_order_describe(grogshopOrderInfo.get("grogshop_order_describe").toString());
			grogshopOrder.setReserve_id(Integer.valueOf(grogshopOrderInfo.get("reserve_id").toString()));
			log.debug(grogshopOrder);
			if(orderDaoImpl.addGrogshopOrderInfo(grogshopOrder) > 0){
				log.debug("订单信息插入成功");
			}else{
				log.debug("订单信息插入失败");
				return null;
			}
			//插入通知
			noticeDaoImpl.addNotice("用户"+user_id+"在您这预定了房子", user_id);
			//插入入住人表
			for(Map<String, Object> checkInPersonMap : checkInPersonInfoMap){
				CheckInPerson checkInPerson = new CheckInPerson();
				checkInPerson.setGrogshop_order_id(uuID);
				checkInPerson.setCheck_in_person_name(checkInPersonMap.get("check_in_person_name").toString());
				checkInPerson.setCheck_in_person_ID_card(checkInPersonMap.get("check_in_person_ID_card").toString());
				if(checkInPersonDaoImpl.addCheckInPerson(checkInPerson)>0){
					log.debug("插入入住人信息成功");
				}else{
					log.debug("插入入住人信息失败");
					return null;
				}
			}
		} catch (SQLException e1) {
			log.error("数据库操作异常");
			return null;
		} catch (ParseException e) {
			log.error("Date型转换异常");
			return null;
		}
		return "订单支付成功";
	}
	/**
	 * 通过用户ID获取此用户的所有订单信息
	 */
	public List<Map<String, Object>> getGrogshopOrderInfoByUserID(Integer user_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			List<GrogshopOrder> orderList = orderDaoImpl.getGrogshopOrderInfoByUserID(user_id);
			for(GrogshopOrder order : orderList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("price", order.getPrice());
				map.put("grogshop_order_state", order.getGrogshop_order_state());
				Reserve reserve = reserveDaoImpl.getReserveInfoByID(order.getReserve_id());
				map.put("reserve_date", reserve.getReserve_date());
				map.put("reserve_day_number", reserve.getReserve_day_number());
				map.put("check_out_date", reserve.getCheck_out_date());
				House house = houseDaoImpl.getHouseInfoByID(reserve.getHouse_id());
				map.put("house_name", house.getHouse_name());
				HouseImg houseImg = houseImgDaoImpl.getHouseImgByHouseID(reserve.getHouse_id()).get(0);
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
	 * @param user_id
	 * @return
	 */
	public List<Map<String, Object>> getGrogshopOrderInfoByLandlordID(
			Integer user_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			List<House> houseList = houseDaoImpl.getHouseByID(user_id);
			for(House house : houseList){
				List<Reserve> reserveList = reserveDaoImpl.getReserveByHouseID(house.getHouse_id());
				for(Reserve reserve : reserveList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("reserve_date", reserve.getReserve_date());
					map.put("reserve_day_number", reserve.getReserve_day_number());
					map.put("check_out_date", reserve.getCheck_out_date());
					map.put("house_name", house.getHouse_name());
					GrogshopOrder order = orderDaoImpl.getGrogshopOrderInfoByReserveID(reserve.getReserve_id());
					map.put("price", order.getPrice());
					map.put("grogshop_order_state", order.getGrogshop_order_state());
					User user = userDaoImpl.getUserInfoById(order.getUser_id());
					map.put("user_name", user.getUser_name());
					map.put("real_name", user.getReal_name());
					map.put("user_phone", user.getUser_phone());
					map.put("user_headimg_url", user.getUser_headimg_url());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			log.error("数据库查询异常");
		}
		return list;
	}
	
}

package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.dao.GrogshopOrderDao;
import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.entity.GrogshopOrder;
import com.zj.service.imp.GrogshopOrderServiceImpl;

/**
 * 订单服务层
 * @author LanceEdward
 *
 */
public class GrogshopOrderService implements GrogshopOrderServiceImpl{
	private GrogshopOrderDaoImpl orderDaoImpl = new GrogshopOrderDao();
	
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
					map.put("landlord_id", allOrder.get(i).getLandlord_id());
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
				map.put("landlord_id", order.getLandlord_id());
				map.put("price", order.getPrice());
				map.put("place_an_order_date", order.getGrogshop_order_state());
				map.put("grogshop_order_state", order.getGrogshop_order_state());
				map.put("grogshop_order_describe", order.getGrogshop_order_describe());
				map.put("reserve_id", order.getReserve_id());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}

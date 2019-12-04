package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.util.PageUtil;

import com.zj.dao.HouseCommentDao;
import com.zj.dao.HouseDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.House;
import com.zj.entity.HouseComment;
import com.zj.entity.User;
import com.zj.service.impl.HouseCommentServiceImpl;

public class HouseCommentService implements HouseCommentServiceImpl{

	private HouseCommentDaoImpl hCommenDaoImpl = new HouseCommentDao();
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private UserDaoImpl  userDaoImpl = new UserDao(); 
	
	/**
	 * 将所有的房间评论打包成list返回
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllHouseComment(Integer hCommentPresentPage,Integer pageSize) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 订单分页
		Long hCommentCount = hCommenDaoImpl.queryCountHouseComment();
		PageUtil<HouseComment> pu = new PageUtil<HouseComment>();
		pu.setCountRow(hCommentCount.intValue());
		pu.setCurrentPage(hCommentPresentPage);
		pu.setPageSize(pageSize);
		
		int hCommentStartRow = pu.getStartRow();
		int hCommentPageSize = pu.getPageSize();
		
		List<HouseComment> hCommentOrder = hCommenDaoImpl.queryHouseCommentPage(hCommentStartRow, hCommentPageSize);
		List<User> user = userDaoImpl.getAllUserInfo();
		List<House> house = houseDaoImpl.getAllHouseInfo();
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		dataMap.put("hComment", hCommentOrder);
		dataMap.put("user", user);
		dataMap.put("house", house);
		
		pu.setMap(dataMap);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageUtil", pu);
		return map;
	}
}

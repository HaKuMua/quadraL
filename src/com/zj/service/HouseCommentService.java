package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.dao.HouseCommentDao;
import com.zj.dao.HouseDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.Comment;
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
	 */
	public List<Map<String, Object>> getAllHouseComment(){
		List<Map<String, Object>> list = null;
		try {
			List<HouseComment> hCommentList = hCommenDaoImpl.getAllHouseComment();
			if(hCommentList != null){
				list = new ArrayList<Map<String,Object>>();
				for(HouseComment hComment : hCommentList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("houseCom_id", hComment.getHouseCom_id());
					map.put("house_id", hComment.getHouse_id());
					//获取房子名
					Integer house_id = hComment.getHouse_id();
					House house = houseDaoImpl.getHouseInfoByID(house_id);
					map.put("house_name", house.getHouse_name());
					map.put("user_id", hComment.getUser_id());
					//获取用户名
					Integer user_id = hComment.getUser_id();
					User user = userDaoImpl.getUserInfoById(user_id);
					map.put("user_name", user.getUser_name());
					map.put("houseCom_content", hComment.getHouseCom_content());
					map.put("houseCom_date", hComment.getHouseCom_date());
					map.put("replier_id", hComment.getReplier_id());
					//获取回复人姓名
					
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

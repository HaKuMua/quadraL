package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.dao.CommentDao;
import com.zj.dao.HouseCommentDao;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.entity.Comment;
import com.zj.entity.HouseComment;
import com.zj.service.imp.HouseCommentServiceImpl;

public class HouseCommentService implements HouseCommentServiceImpl{

	private HouseCommentDaoImpl hCommenDaoImpl = new HouseCommentDao();
	
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
					map.put("user_id", hComment.getUser_id());
					map.put("houseCom_content", hComment.getHouseCom_content());
					map.put("houseCom_date", hComment.getHouseCom_date());
					map.put("replier_id", hComment.getReplier_id());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

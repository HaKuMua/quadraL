package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.util.PageUtil;

import com.zj.dao.GrogshopOrderDao;
import com.zj.dao.HouseCommentDao;
import com.zj.dao.HouseDao;
import com.zj.dao.HouseImgDao;
import com.zj.dao.NoticeDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.NoticeDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.Comment;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.House;
import com.zj.entity.HouseComment;
import com.zj.entity.HouseImg;
import com.zj.entity.User;
import com.zj.service.impl.HouseCommentServiceImpl;

public class HouseCommentService implements HouseCommentServiceImpl {
	private GrogshopOrderDaoImpl orderDaoImpl = new GrogshopOrderDao();
	private NoticeDaoImpl noticeDaoImpl = new NoticeDao();
	private HouseCommentDaoImpl hCommenDaoImpl = new HouseCommentDao();
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private UserDaoImpl userDaoImpl = new UserDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private Logger log = Logger.getLogger(HouseCommentService.class);

	/**
	 * 将所有的房间评论打包成list返回
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllHouseComment(Integer limit,Integer page) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<HouseComment> houseCommentList = null;
		try {
			houseCommentList = hCommenDaoImpl.queryHouseCommentPage((page-1)*10, limit);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(HouseComment hComment : houseCommentList){
				Map<String, Object> hCommentMap = new HashMap<String, Object>();
				hCommentMap.put("houseCom_id", hComment.getHouseCom_id());
				hCommentMap.put("house_name", houseDaoImpl.getHouseInfoByID(hComment.getHouse_id()).getHouse_name());
				hCommentMap.put("user_name", userDaoImpl.getUserInfoById(hComment.getUser_id()).getUser_name());
				hCommentMap.put("houseCom_content", hComment.getHouseCom_content());
				hCommentMap.put("houseCom_date", hComment.getHouseCom_date());
				list.add(hCommentMap);
			}
			map.put("data", list);
			map.put("count", Integer.valueOf(hCommenDaoImpl.queryCountHouseComment().toString()));
			map.put("msg", "");
			map.put("code", 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 通过房东ID获取他的房子的所有评论
	 */
	public List<Map<String, Object>> getHouseCommentByLandlordID(Integer user_id) {
		List<Map<String, Object>> commentListMap = new ArrayList<Map<String, Object>>();
		try {
			List<House> houseList = houseDaoImpl.getHouseByID(user_id);
			for (House house : houseList) {
				Map<String, Object> map = new HashMap<String, Object>();
				List<HouseComment> commentList = hCommenDaoImpl
						.getHouseCommentByHouseID(house.getHouse_id());
				for (HouseComment houseComment : commentList) {
					Map<String, Object> commentMap = new HashMap<String, Object>();
					commentMap.put("user_id", houseComment.getUser_id());
					User user = userDaoImpl.getUserInfoById(houseComment
							.getUser_id());
					commentMap.put("user_name", user.getUser_name());
					commentMap.put("user_headimg_url",
							user.getUser_headimg_url());
					commentMap.put("houseCom_date",
							houseComment.getHouseCom_date());
					commentMap.put("houseCom_content",
							houseComment.getHouseCom_content());
					commentMap.put("house_id", house.getHouse_id());
					commentMap.put("house_name", house.getHouse_name());
					HouseImg houseImg = houseImgDaoImpl.getHouseImgByHouseID(
							house.getHouse_id()).get(0);
					commentMap
							.put("house_img_url", houseImg.getHouse_img_url());
					commentListMap.add(commentMap);
				}

			}
		} catch (SQLException e) {
			log.error("数据库操作异常");
		}
		return commentListMap;
	}

	/**
	 * 完成酒店订单，增并且加一条评论
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> addHouseComment(Map<String, Object> info) {
		HouseComment comment = new HouseComment();
		Map<String, Object> map = new HashMap<String, Object>();
		// 给文章实体类set值进去
		if (info.get("user_id") != null)
			comment.setUser_id(Integer.valueOf(info.get("user_id").toString()));
		if (info.get("house_id") != null)
			comment.setHouse_id(new Integer(info.get("house_id").toString()));
		if (info.get("houseCom_content") != null)
			comment.setHouseCom_content(info.get("houseCom_content").toString());
		if (info.get("replier_id") != null)
			comment.setReplier_id(Integer.valueOf(info.get("replier_id")
					.toString()));
		int count = -1;
		try {
			count = hCommenDaoImpl.addComment(comment);
			//改变订单状态
			count = orderDaoImpl.updateUserStatus(info.get("order_id").toString(), 2);
			count = orderDaoImpl.updateLandlordStatus(info.get("order_id").toString(), 3);
			// 插入通知
			count = noticeDaoImpl.addNotice("账户已收到"+info.get("price").toString()+"元", Integer.valueOf(info.get("landlord_id").toString()));
			if (count > 0) {
				map.put("msg", "订单已完成！");
			} else {
				map.put("msg", "失败！");
			}
		} catch (SQLException e) {
			map.put("msg", "失败！");
			log.error("失败");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 通过userID获取他写的所有评论
	 */
	public List<Map<String, Object>> getUserComment(Integer user_id) {
		List<Map<String, Object>> commentListMap = new ArrayList<Map<String, Object>>();
		try {
			List<HouseComment> commentList = hCommenDaoImpl.getUserComment(user_id);
			for (HouseComment houseComment : commentList) {
				Map<String, Object> commentMap = new HashMap<String, Object>();
				commentMap.put("user_id", houseComment.getUser_id());
				House house = houseDaoImpl.getHouseInfoByID(houseComment.getHouse_id());
				commentMap.put("houseCom_date",
						houseComment.getHouseCom_date());
				commentMap.put("houseCom_content",
						houseComment.getHouseCom_content());
				commentMap.put("house_id", house.getHouse_id());
				commentMap.put("house_name", house.getHouse_name());
				HouseImg houseImg = houseImgDaoImpl.getHouseImgByHouseID(
						house.getHouse_id()).get(0);
				commentMap
						.put("house_img_url", houseImg.getHouse_img_url());
				commentListMap.add(commentMap);
			}
		} catch (SQLException e) {
			log.error("数据库操作异常");
		}
		return commentListMap;
	}
	/**
	 * 删除一篇房子评论
	 */
	public Integer deleteHouseComm(Integer houseComment_id) {
		try {
			Integer dal = hCommenDaoImpl.deleteHouseCommentByID(houseComment_id);
			if(dal > 0)
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			log.error("数据库操作异常");
			return -1;
		}
	}
}

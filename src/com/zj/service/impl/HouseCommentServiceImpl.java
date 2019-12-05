package com.zj.service.impl;

import java.util.List;
import java.util.Map;

public interface HouseCommentServiceImpl {

	/**
	 * 将所有的房间评论打包成list返回方法接口
	 */
	List<Map<String, Object>> getAllHouseComment();
	/**
	 * 通过房东ID获取他的房子的所有评论
	 */
	List<Map<String, Object>> getHouseCommentByLandlordID(Integer user_id);
	
	/**
	 * 增加一条评论
	 * @param info
	 * @return
	 */
	Map<String, Object> addComment(Map<String, Object> info);
	
	/**
	 * 通过userID获取他写的所有评论
	 */
	List<Map<String, Object>> getUserComment(Integer user_id);
}

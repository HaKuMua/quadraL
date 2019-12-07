package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HouseCommentServiceImpl {

	/**
	 * 将所有的房间评论打包成map返回方法接口
	 */
	Map<String, Object> getAllHouseComment(Integer hCommentPresentPage,Integer pageSize) throws SQLException;
	/**
	 * 通过房东ID获取他的房子的所有评论
	 */
	List<Map<String, Object>> getHouseCommentByLandlordID(Integer user_id);
	
	/**
	 * 增加一条评论
	 * @param info
	 * @return
	 */
	Map<String, Object> addHouseComment(Map<String, Object> info);
	
	/**
	 * 通过userID获取他写的所有评论
	 */
	List<Map<String, Object>> getUserComment(Integer user_id);
	/**
	 * 删除一篇房子评论
	 */
	Integer deleteHouseComm(Integer houseComment_id);
}

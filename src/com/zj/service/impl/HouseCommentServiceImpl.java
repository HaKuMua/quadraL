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
	List<Map<String, Object>> getHouseCommentByLendlordID(Integer user_id);
}

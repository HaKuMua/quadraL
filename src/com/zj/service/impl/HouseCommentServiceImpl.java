package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HouseCommentServiceImpl {

	/**
	 * 将所有的房间评论打包成map返回方法接口
	 */
	Map<String, Object> getAllHouseComment(Integer hCommentPresentPage,Integer pageSize) throws SQLException;
}

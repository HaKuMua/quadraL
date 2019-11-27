package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.HouseComment;

/**
 * 
 * @author lijia
 *房子评论数据库操作接口
 */
public interface HouseCommentDaoImpl {
	/**
	 * 通过房子ID获取此房子的所有评论方法接口
	 * @return
	 * @throws SQLException 
	 */
	List<HouseComment> getHouseCommentByHouseID(Integer HouseID) throws SQLException;
}

package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Comment;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.HouseComment;

/**
 * 
 * @author lijia
 *房子评论数据库操作接口
 */
public interface HouseCommentDaoImpl {
	/**
	 * 获取房子所有评论接口
	 */
	List<HouseComment> getAllHouseComment() throws SQLException;
	/**
	 * 获取房子分页接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<HouseComment> queryHouseCommentPage(Integer startRow,Integer pageSize)  throws SQLException;
	/**
	 * 获取房子总页数接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Long queryCountHouseComment()  throws SQLException;
	/**
	 * 通过房子ID获取此房子的所有评论方法接口
	 * @return
	 * @throws SQLException 
	 */
	List<HouseComment> getHouseCommentByHouseID(Integer HouseID) throws SQLException;
	
	/**
	 * 给房子添加评论
	 * @throws SQLException 
	 */
	Integer addComment(HouseComment comment) throws SQLException;
	
	/**
	 * 获得user_id的评论
	 * @throws SQLException 
	 */
	List<HouseComment> getUserComment(Integer user_id) throws SQLException ;
}

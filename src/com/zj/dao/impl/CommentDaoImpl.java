package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Comment;
import com.zj.entity.Landlord;

/**
 * 
 * @author lijia
 *评论数据库操作接口
 */
public interface CommentDaoImpl {
	/*
	 * 获取所有评论方法接口
	 */
	public List<Comment> getAllCommen() throws SQLException;
}

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
	/**
	 * 给文章添加评论
	 * @throws SQLException 
	 */
	int addComment(Integer article_id,Integer user_id,String comment_content,String replier_id) throws SQLException;
	/**
	 * 通过id删除文章评论
	 * @throws SQLException 
	 */
	int deleteComment( Integer comment_id) throws SQLException;
	
	/**
	 * 通过id查询评论
	 * @throws SQLException 
	 */
	Comment queryCommentById(Integer comment_id) throws SQLException;
	/**
	 * 分页查询一篇文章评论
	 * @throws SQLException 
	 */
	List<Comment> queryPageComment(Integer article_id,int startRow,int pageSize) throws SQLException;
	/**
	 * 不分页查询一篇文章所有评论
	 * @throws SQLException 
	 */
	List<Comment> queryAllComment(Integer article_id) throws SQLException;
	/**
	 * 通过id查询一篇文章评论数量
	 */
	Long queryCommCount(Integer article_id) throws SQLException;
	/**
	 * 查询一条评论的回复数量
	 * @throws SQLException 
	 */
	Long queryReplierCount(Integer comment_id) throws SQLException;
	/**
	 * 查询一条评论的赞数量
	 * @throws SQLException 
	 */
	Long queryPraiseCount(Integer comment_id) throws SQLException;
}

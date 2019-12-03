package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Comment;

/**
 * 
 * @author lijia
 *评论数据库操作接口
 */
public interface CommentDaoImpl {
	/**
	 * 获取所有评论方法接口
	 */
	List<Comment> getAllComment() throws SQLException;
	/**
	 * 给文章添加评论
	 * @throws SQLException 
	 */
	int addComment(Comment commInfo) throws SQLException;
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
	List<Comment> queryPageComment(Integer article_id,Integer startRow,Integer pageSize) throws SQLException;
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
	/**
	 * 通过文章id删除一篇文章的所有评论
	 * @throws SQLException 
	 */
	Integer deleteCommByArticleId(Integer article_id) throws SQLException;
}

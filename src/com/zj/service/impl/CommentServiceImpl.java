
package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author lijia
 *评论服务层
 */
public interface CommentServiceImpl {
	/**
	 * 将所有的评论打包成map返回方法接口
	 */
	Map<String, Object> getAllComment(Integer commentPresentPage,Integer pageSize) throws SQLException;
	/*
	 * 评论分页
	 */
	List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException;
}

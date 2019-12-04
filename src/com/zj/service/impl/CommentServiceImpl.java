
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
	 * 将所有的评论打包成list返回方法接口
	 */
	List<Map<String, Object>> getAllComment();
	/**
	 * 评论分页
	 */
	List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException;
	/**
	 * 增加一条评论
	 * @param info
	 * @return
	 */
	Map<String, Object> addComment(Map<String, Object> info);

}


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
	/**
	 * 删除一条评论
	 */
	Integer deleteComm(Integer comment_id);

}

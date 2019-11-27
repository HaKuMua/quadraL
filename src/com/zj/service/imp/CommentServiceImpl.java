package com.zj.service.imp;

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
}

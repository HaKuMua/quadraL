package com.zj.service.imp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CommentServiceImpl {
	List<Map<String, Object>> getAllComment();
	/*
	 * 评论分页
	 */
	List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException;
}

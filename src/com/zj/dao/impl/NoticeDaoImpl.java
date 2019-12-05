package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Notice;

/**
 * 
 * @author lijia
 *通知数据库操作接口
 */
public interface NoticeDaoImpl {
	/**
	 * 添加一条通知方法接口
	 */
	int addNotice(String notice_content,Integer user_id) throws SQLException;
	/**
	 * 通过用户ID获得一组通知信息
	 * @param user_id 用户id
	 * @return
	 * @throws SQLException
	 */
	List<Notice> getNoticeByUserID(Integer user_id) throws SQLException;
	
	/**
	 * 改变通知信息状态
	 * @param user_id 用户id
	 * @return
	 * @throws SQLException
	 */
	Integer updateNotice(Integer user_id) throws SQLException;
}

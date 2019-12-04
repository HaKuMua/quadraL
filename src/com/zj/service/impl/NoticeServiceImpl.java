package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.util.GetConn;

/**
 * 通知Service层接口
 * @author lijia
 *
 */
public interface NoticeServiceImpl {
	/**
	 * 通过用户ID获得一组通知信息
	 * @param user_id 用户id
	 * @return
	 */
	List<Map<String, Object>> getNoticeInfoByUserID(Integer user_id);
	
	/**
	 * 改变通知信息状态
	 * @param user_id 用户id
	 * @return
	 * @throws SQLException
	 */
	Boolean updateNotice(Integer user_id);
}

package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.NoticeDaoImpl;
import com.zj.entity.Notice;
/**
 * 
 * @author lijia
 *通知数据库操作DAO
 */
public class NoticeDao implements NoticeDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 添加一条通知
	 */
	public int addNotice(String notice_content, Integer user_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into notice(user_id,notice_content) value(?,?)";
		int data = qr.update(conn, sql, user_id,notice_content);
		conn.close();
		return data;
		
	}
	
	/**
	 * 通过用户ID获得一组通知信息
	 * @param user_id 用户id
	 * @return
	 * @throws SQLException
	 */
	public List<Notice> getNoticeByUserID(Integer user_id) throws SQLException{
		conn = GetConn.getConn();
		String sql = "select * from notice where user_id=?";
		List<Notice> data = qr.query(conn, sql, new BeanListHandler<Notice>(Notice.class), user_id);
		conn.close();
		return data;
	}

}

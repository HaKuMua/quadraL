package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.entity.HouseImg;
/**
 * 
 * @author lijia
 *房子图片数据库操作实现类
 */
public class HouseImgDao implements HouseImgDaoImpl{
	/**
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 获取所有房子图片方法
	 */
	public List<HouseImg> getAllHouseImgInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_img";
		List<HouseImg> data = qr.query(conn, sql, new BeanListHandler<HouseImg>(HouseImg.class));
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过房子图片ID获取单个房子图片方法
	 */
	public HouseImg getHouseImgInfoByID(Integer HouseImgID) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_img where house_img_id=?";
		HouseImg data = qr.query(conn, sql, new BeanHandler<HouseImg>(HouseImg.class),HouseImgID);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过房子ID获取一组房子图片方法
	 */
	public List<HouseImg> getHouseImgByHouseID(Integer HouseID) throws SQLException{
		conn = GetConn.getConn();
		String sql = "select * from house_img where house_id=?";
		List<HouseImg> data = qr.query(conn, sql, new BeanListHandler<HouseImg>(HouseImg.class),HouseID);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 添加一个房子图片信息方法
	 * @param houseImg 传递一个房子图片实体类进来
	 * @return 返回值若大于0则插入成功
	 * @throws SQLException
	 */
	public int addHouseImgInfo(HouseImg houseImg) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into house_img(house_id,house_img_url) value(?,?) ";
		int data = qr.update(conn, sql, houseImg.getHouse_id(),houseImg.getHouse_img_url());
		GetConn.closeConn(conn);
		return data;
	}
	
	
}

package com.zj.dao.realize;

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
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	public List<HouseImg> getAllHouseImgInfo() throws SQLException {
		String sql = "select * from house_img";
		return qr.query(conn, sql, new BeanListHandler<HouseImg>(HouseImg.class));
	}
	public HouseImg getHouseImgInfoByID(Integer HouseImgID) throws SQLException {
		String sql = "select * from house_img where house_img_id=?";
		return qr.query(conn, sql, new BeanHandler<HouseImg>(HouseImg.class),HouseImgID);
	}
	public List<HouseImg> getHouseImgByHouseID(Integer HouseID) throws SQLException{
		String sql = "select * from house_img where house_id=?";
		return qr.query(conn, sql, new BeanListHandler<HouseImg>(HouseImg.class),HouseID);
	}
	
}

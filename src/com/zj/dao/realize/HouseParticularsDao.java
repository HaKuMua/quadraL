package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zj.dao.impl.HouseParticularsDaoImpl;
import com.zj.entity.HouseParticulars;

import cn.com.uitl.GetConn;

/**
 * 
 * @author lijia
 *房子详情数据库操作实现类
 */
public class HouseParticularsDao implements HouseParticularsDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	public List<HouseParticulars> getAllHouseParticularsInfo()
			throws SQLException {
		String sql = "select * from house_particulars";
		return qr.query(conn, sql, new BeanListHandler<HouseParticulars>(HouseParticulars.class));
	}
	public HouseParticulars getHouseParticularsInfoByID(
			Integer HouseParticularsID) throws SQLException {
		String sql = "select * from house_particulars where house_particulars_id=?";
		return qr.query(conn, sql, new BeanHandler<HouseParticulars>(HouseParticulars.class),HouseParticularsID);
	}

}

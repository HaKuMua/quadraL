package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.entity.GrogshopOrder;

/**
 * 订单数据库操作实现类
 * 
 * @author LanceEdward
 * 
 */
public class GrogshopOrderDao implements GrogshopOrderDaoImpl {
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;

	/**
	 * 获取所有酒店订单信息DAO层方法
	 * @return
	 * @throws SQLException
	 */
	public List<GrogshopOrder> getAllGrogshopOrderInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order";
		List<GrogshopOrder> data =  qr.query(conn, sql, new BeanListHandler<GrogshopOrder>(
				GrogshopOrder.class));
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过ID获取酒店订单信息DAO层
	 * @param GrogshopOrderID
	 * @return
	 * @throws SQLException
	 */
	public GrogshopOrder getGrogshopOrderInfoByID(Integer GrogshopOrderID)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order where grogshop_order_id = ?";
		GrogshopOrder data =   qr.query(conn, sql,
				new BeanHandler<GrogshopOrder>(GrogshopOrder.class),
				GrogshopOrderID);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 添加酒店订单信息DAO层
	 * @param grogshopOrder
	 * @return
	 * @throws SQLException
	 */
	public int addGrogshopOrderInfo(GrogshopOrder grogshopOrder)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into grogshop_order(grogshop_order_id,user_id,price" +
				",grogshop_order_describe,reserve_id) value(?,?,?,?,?)";
		int data = qr.update(conn, sql,grogshopOrder.getGrogshop_order_id() ,grogshopOrder.getUser_id(),grogshopOrder.getPrice()
				,grogshopOrder.getGrogshop_order_describe(),grogshopOrder.getReserve_id());
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过用户ID查询此用户的所有订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public List<GrogshopOrder> getGrogshopOrderInfoByUserID(Integer user_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order where user_id = ?";
		List<GrogshopOrder> data = qr.query(conn, sql,
				new BeanListHandler<GrogshopOrder>(GrogshopOrder.class),
				user_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过预订ID获得单个订单信息
	 */
	public GrogshopOrder getGrogshopOrderInfoByReserveID(Integer reserve_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order where reserve_id = ?";
		GrogshopOrder data = qr.query(conn, sql,
				new BeanHandler<GrogshopOrder>(GrogshopOrder.class),
				reserve_id);
		GetConn.closeConn(conn);
		return data;
	}

}

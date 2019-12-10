package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.entity.CheckInPerson;
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
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<GrogshopOrder> getAllGrogshopOrderInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order";
		List<GrogshopOrder> data = qr.query(conn, sql,
				new BeanListHandler<GrogshopOrder>(GrogshopOrder.class));
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 获取订单分页
	 */
	public List<GrogshopOrder> queryOrderPage(Integer startRow, Integer pageSize)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order limit ?,?";
		System.out.println(startRow);
		System.out.println(pageSize);
		List<GrogshopOrder> data = qr.query(conn, sql,
				new BeanListHandler<GrogshopOrder>(GrogshopOrder.class),
				startRow, pageSize);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 订单总页数
	 */
	public Long queryCountOrder() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from grogshop_order";
		Long data = qr.query(conn, sql, new ScalarHandler<Long>());
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过ID获取酒店订单信息DAO层
	 * 
	 * @param GrogshopOrderID
	 * @return
	 * @throws SQLException
	 */
	public GrogshopOrder getGrogshopOrderInfoByID(String GrogshopOrderID)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order where grogshop_order_id = ?";
		GrogshopOrder data = qr.query(conn, sql,
				new BeanHandler<GrogshopOrder>(GrogshopOrder.class),
				GrogshopOrderID);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 添加酒店订单信息DAO层
	 * 
	 * @param grogshopOrder
	 * @return
	 * @throws SQLException
	 */
	public int addGrogshopOrderInfo(GrogshopOrder grogshopOrder)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into grogshop_order(grogshop_order_id,user_id,price"
				+ ",grogshop_order_describe,reserve_id) value(?,?,?,?,?)";
		int data = qr.update(conn, sql, grogshopOrder.getGrogshop_order_id(),
				grogshopOrder.getUser_id(), grogshopOrder.getPrice(),
				grogshopOrder.getGrogshop_order_describe(),
				grogshopOrder.getReserve_id());
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
	 * 通过用户ID查询此用户的所有已完成订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public List<GrogshopOrder> getFinishOrderInfoByUserID(Integer user_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order where user_id = ? and (grogshop_order_state = 2 or grogshop_order_state = 3)";
		List<GrogshopOrder> data = qr.query(conn, sql,
				new BeanListHandler<GrogshopOrder>(GrogshopOrder.class),
				user_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过用户ID查询此用户的所有未完成订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public List<GrogshopOrder> getNotOrderInfoByUserID(Integer user_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from grogshop_order where user_id = ? and  grogshop_order_state = 1";
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
		String sql = "select * from grogshop_order where reserve_id = ? ";
		GrogshopOrder data = qr
				.query(conn, sql, new BeanHandler<GrogshopOrder>(
						GrogshopOrder.class), reserve_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 房东改变订单状态
	 * @param order_id
	 * @param landlordState
	 * @return
	 * @throws SQLException
	 */
	public Integer updateLandlordStatus(String order_id,Integer landlordState )
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "update grogshop_order set grogshop_order_landlordstate = ? where grogshop_order_id=?";
		Integer data = qr.update(conn, sql,landlordState, order_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 用户改变订单状态
	 * @param order_id
	 * @param userState
	 * @return
	 * @throws SQLException
	 */
	public Integer updateUserStatus(String order_id,Integer userState )
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "update grogshop_order set grogshop_order_state = ? where grogshop_order_id=?";
		Integer data = qr.update(conn, sql,userState, order_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 根据订单id查到房东id
	 * 
	 * @param order_id
	 * @return
	 * @throws SQLException
	 */
	public Integer getHouseUserIdByOrderId(String order_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select user_id from house where house_id =(select house_id from reserve where reserve_id =  (select reserve_id from grogshop_order where grogshop_order_id = ?))";
		Integer data = qr.query(conn, sql, new ScalarHandler<Integer>(),
				order_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 根据房东id查到订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public List<GrogshopOrder> getOrderByHouseUserId(Integer user_id,Integer state,Integer startRow,Integer pageSize) throws SQLException {
		conn = GetConn.getConn();
		String sql = "(select * from grogshop_order where grogshop_order_landlordstate = ? and (reserve_id in ( select reserve_id from reserve where house_id in (select house_id from house where user_id = ?)))) limit ?,?";
		List<GrogshopOrder> data = qr.query(conn, sql,
				new BeanListHandler<GrogshopOrder>(GrogshopOrder.class),
				state,user_id,startRow,pageSize);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 获取一个房东的指定订单信息的数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Long getOrderNumByState(Integer user_id, Integer state)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from grogshop_order where grogshop_order_landlordstate = ? and (reserve_id in ( select reserve_id from reserve where house_id in (select house_id from house where user_id = ?)))";
		Long data = qr.query(conn, sql, new ScalarHandler<Long>(),state,user_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 根据订单id查到所有入住人
	 * 
	 * @param order_id
	 * @return
	 * @throws SQLException
	 */
	public List<CheckInPerson> getPersonByOrderId(String order_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from check_in_person where grogshop_order_id = ? ";
		List<CheckInPerson> data = qr.query(conn, sql,
				new BeanListHandler<CheckInPerson>(CheckInPerson.class),order_id);
		GetConn.closeConn(conn);
		return data;
	}

	

}

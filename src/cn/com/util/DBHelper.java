package cn.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.PreparedStatement;

/**
 *	数据库连接和释放资源工具类
 */
public class DBHelper {
	private static QueryRunner qr ;
	private static ComboPooledDataSource dataSource;
	/**
	 * 静态代码块加载数据库配置
	 */
	static{
		dataSource = new ComboPooledDataSource();
	}
	
	/**
	 * 链接数据库
	 * @return
	 */
	public static Connection getConn(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * 释放资源
	 */
	public static void free(ResultSet rs,PreparedStatement pst, Connection conn){
		try{
			if(rs!=null){
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放资源
	 */
	public static void free(PreparedStatement pst, Connection conn){
		free(null,pst,conn);
	}
	
	public static int update(String sql,Object... params) throws SQLException {
		qr = new QueryRunner();
		int num =  qr.update(getConn(), sql,params);
		return num;
	}
	
	
	public static Long queryCount(String sql,Object... params) throws SQLException {
		qr= new QueryRunner();
		@SuppressWarnings("unchecked")
		Long num = qr.query(getConn(),sql, new ScalarHandler(),params);
		return num;
	}
	
	public static Map<String, Object> queryToMap(String sql, Object... params) throws SQLException {
		qr= new QueryRunner();
		Map<String,Object> map = qr.query(getConn(), sql,new MapHandler(), params);
		return map;
	}
	public static List<Map<String, Object>> queryToListMap(String sql,Object... params) throws SQLException {
		qr= new QueryRunner();
		List<Map<String, Object>> list = qr.query(getConn(), sql, new MapListHandler(),params);
		return list;
	
	}
	public static <T> T queryToBean(String sql,Class<T> clazz,Object... params) throws SQLException{
		qr = new QueryRunner();
		ResultSetHandler<T> rsh = new BeanHandler<T>(clazz);
		T t = qr.query(getConn(),sql, rsh, params);
		return t;
	}
	public static <T> List<T> queryToListBean(String sql,Class<T> clazz,Object...params) throws SQLException {
		qr = new QueryRunner();
		List<T> list = qr.query(getConn(), sql,new BeanListHandler<T>(clazz), params);
		return list;
	}
	
	public static List<Object> queryToList(String sql,Object...params) throws SQLException {
		qr = new QueryRunner();
		List<Object> list = qr.query(sql, new ColumnListHandler(),params);
		return list;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(111);
			System.out.println(queryCount("select count(*) from userinfo"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

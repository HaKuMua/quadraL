package cn.com.uitl;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class GetConn {
	private static ComboPooledDataSource dataSource;
	static{
		dataSource = new ComboPooledDataSource();
	}
	public static Connection getConn(){
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

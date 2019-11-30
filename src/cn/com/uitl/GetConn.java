package cn.com.uitl;

import java.sql.Connection;
import java.sql.SQLException;

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
	public static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

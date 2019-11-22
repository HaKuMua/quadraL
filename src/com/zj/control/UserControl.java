package com.zj.control;

import java.sql.SQLException;

import com.zj.dao.impl.UserDaoImpl;
import com.zj.dao.realize.UserDao;
/**
 * 
 * @author lijia
 *用户数据操作
 */
public class UserControl {
	private static UserDaoImpl userDaoImpl = new UserDao();
	
	public static void main(String[] args) {
		try {
			System.out.println(userDaoImpl.getUserInfoByPhone("15173595627"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

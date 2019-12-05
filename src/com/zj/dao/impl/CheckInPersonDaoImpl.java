package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.CheckInPerson;
import com.zj.entity.GrogshopOrder;

/**
 * 入住人数据库操作接口
 * @author LanceEdward
 *
 */
public interface CheckInPersonDaoImpl {
	/**
	 * 获取入住人员分页接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<CheckInPerson> queryCheckInPersonPage(Integer startRow,Integer pageSize)  throws SQLException;
	/**
	 * 获取入住人员总页数接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Long queryCountCheckInPerson()  throws SQLException;
	/**
	 * 获取所有入住人员信息方法接口
	 */
	List<CheckInPerson> getAllCheckInPersonInfo() throws SQLException;
	/**
	 * 通过入住人员身份证号获取单个用户信息方法接口
	 */
	CheckInPerson getCheckInPersonByIdCard(String check_in_person_ID_card) throws SQLException;
	/**
	 * 添加一个入住人信息方法接口
	 * @param checkInPerson
	 * @return
	 * @throws SQLException
	 */
	int addCheckInPerson(CheckInPerson checkInPerson) throws SQLException;
}

package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.CheckInPerson;

/**
 * 入住人数据库操作接口
 * @author LanceEdward
 *
 */
public interface CheckInPersonDaoImpl {
	/*
	 * 获取所有入住人员信息方法接口
	 */
	public List<CheckInPerson> getAllCheckInPersonInfo() throws SQLException;
	/*
	 * 通过入住人员身份证号获取单个用户信息方法接口
	 */
	public CheckInPerson getCheckInPersonByIdCard(String check_in_person_ID_card) throws SQLException;
}

package com.zj.control;

import java.sql.SQLException;
import java.util.List;

import com.zj.dao.impl.CheckInPersonDaoImpl;
import com.zj.dao.realize.CheckInPersonDao;
import com.zj.entity.CheckInPerson;
import com.zj.entity.User;

/**
 * 入住人员操作类
 * @author LanceEdward
 *
 */
public class CheckInPersonControl {
	private CheckInPersonDaoImpl cPDaoImpl = new CheckInPersonDao();
	
	public List<CheckInPerson> getAllCheckInPersonInfo() throws SQLException {
		return cPDaoImpl.getAllCheckInPersonInfo();
	}
	
	public CheckInPerson getCheckInPersonInfoByIdCard(String check_in_person_ID_card) throws SQLException {
		return cPDaoImpl.getCheckInPersonByIdCard(check_in_person_ID_card);
	}
}

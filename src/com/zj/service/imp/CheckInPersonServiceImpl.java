package com.zj.service.imp;

import java.util.List;
import java.util.Map;

public interface CheckInPersonServiceImpl {
	List<Map<String, Object>> getAllCheckInPersonInfo();
	Map<String, Object> getCheckInPersonInfoByIdCard(String check_in_person_ID_card);
}

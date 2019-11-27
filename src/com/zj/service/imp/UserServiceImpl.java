package com.zj.service.imp;

import java.util.List;
import java.util.Map;

public interface UserServiceImpl {
	List<Map<String, Object>> getAllUserInfo();
	Map<String, Object> getUserInfoByPhone(String user_phone);
}

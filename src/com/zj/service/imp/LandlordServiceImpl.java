package com.zj.service.imp;

import java.util.List;
import java.util.Map;

public interface LandlordServiceImpl {
	List<Map<String, Object>> getAllLandlordInfo();
	Map<String, Object> getLandlordInfoByPhone(String landlord_phone);
	String addLandlordInfo(Map<String, Object> map);
}

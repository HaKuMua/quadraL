package com.zj.service.imp;

import java.util.List;
import java.util.Map;

public interface HouseServiceImpl {
	List<Map<String, Object>> getAllHouseInfo();
	Map<String, Object> getHouseInfoByID(Integer house_id);
}

package com.zj.service.imp;

import java.util.List;
import java.util.Map;

public interface GrogshopOrderServiceImpl {
	List<Map<String, Object>> getAllGrogshopOrderInfo();
	Map<String, Object> getAllGrogshopOrderInfoByID(Integer grogshop_order_id);
}

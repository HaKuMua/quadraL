package com.zj.service.imp;

import java.util.List;
import java.util.Map;

/**
 * 预定信息接口
 * @author LanceEdward
 *
 */
public interface ReserveServiceImpl {
	/*
	 * 将所有预定信息包装成一个list<map>返回
	 */
	List<Map<String, Object>> getAllReserve();
}

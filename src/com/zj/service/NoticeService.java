package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zj.dao.NoticeDao;
import com.zj.dao.impl.NoticeDaoImpl;
import com.zj.entity.Notice;
import com.zj.service.impl.NoticeServiceImpl;

/**
 * 通知Service层
 * @author lijia
 *
 */
public class NoticeService implements NoticeServiceImpl{
	private NoticeDaoImpl noticeDaoImpl = new NoticeDao();
	private Logger log = Logger.getLogger(NoticeService.class);
	/**
	 * 通过用户ID获得一组通知信息
	 * @param user_id 用户id
	 * @return
	 */
	public List<Map<String, Object>> getNoticeInfoByUserID(Integer user_id) {
		List<Map<String, Object>> hoticeList = null;
		try {
			List<Notice> list = noticeDaoImpl.getNoticeByUserID(user_id);
			if(list != null){
				hoticeList = new ArrayList<Map<String,Object>>();
				for(Notice notice : list){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("notice_content", notice.getNotice_content());
					map.put("notice_date", notice.getNotice_date());
					hoticeList.add(map);
				}
			}
		} catch (SQLException e) {
			log.error("数据库查询异常");
		}
		return hoticeList;
	}

}

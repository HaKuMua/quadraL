package com.zj.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONObject;

import cn.com.util.BaseServlet;
import cn.com.util.CheckoutPhoneNumber;
import cn.com.util.CloudInfDemo;
import cn.com.util.FileLoadServletUtil;
import cn.com.util.NumberUtil;

import com.alibaba.fastjson.JSON;
import com.zj.service.NoticeService;
import com.zj.service.UserService;
import com.zj.service.impl.NoticeServiceImpl;
import com.zj.service.impl.UserServiceImpl;
/**
 * 用户
 * @author ml
 *
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userServiceImpl = new UserService();
	private NoticeServiceImpl noticeServiceImpl = new NoticeService();
	private String map;
	private String callback;
	private String user_id;
	private String user_phone;
//	private Logger log = new 
	/**
	 * 邮箱/电话密码登录
	 * @throws IOException 
	 */
	public void loginByCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(map);
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.loginByCode(myMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback + "(" + obj + ")");
	}
	
	/**
	 * 手机号登录，若手机号存在则用户注册
	 * @throws IOException 
	 */
	public void loginByPhone(HttpServletRequest request,HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String user_phone=myMap.get("user_phone").toString();
		Integer code=new Integer(request.getSession().getAttribute("code").toString());
		Integer myCode =new Integer( myMap.get("code").toString());
		sendMap = userServiceImpl.loginByPhone(user_phone,myCode,code);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	/**
	 * 用户设置密码
	 * @throws IOException 
	 */
	public void setUserPwd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.setUserPwd(myMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback+"("+obj+")");
}
	
	/**
	 * 信息基本修改
	 * @throws IOException 
	 */
	public void updateBasicInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.updateBasicInfo(myMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	/**
	 * 用户修改密码
	 * @throws IOException 
	 */
	public void updateUserPwd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(map);
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.updateUserPwd(myMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	/**
	 * 上传头像并修改头像
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws IOException
	 */
	public void uploadImg(HttpServletRequest request,HttpServletResponse response) throws FileUploadException, IOException {
		// 图片上传并且返回保存的路径
		String url = FileLoadServletUtil.upload(request, response,
				"D:/quadraL/userImg/");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		HashMap<String, Object> data = new HashMap<String, Object>();
		//将图片地址存到数据库(因为浏览器不能直接访问本地路径，会报错)；
		String dataBaseUrl="/image"+url.substring(10);
		data.put("src", dataBaseUrl);
		data.put("userInfo", userServiceImpl.addUserHead(new Integer(user_id), dataBaseUrl));
		map.put("data", data);
		JSONObject obj = new JSONObject(map);
		// 如果上传成功返回1
		response.getWriter().print(obj);
	}

	/**
	 * 获取手机验证码
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws IOException
	 */
	public void getPhoneCode(HttpServletRequest request,HttpServletResponse response) throws FileUploadException, IOException {
		String code = NumberUtil.newCode();
		Map<String, Object> hintMap = new HashMap<String, Object>();
		//检验手机号是否正确
		if(CheckoutPhoneNumber.isPhoneNumberValid(user_phone)){
			hintMap.put("hint", "发送成功！");
			CloudInfDemo.sendSmsCode(user_phone, code);
			request.getSession().setAttribute("code", code);
		}else{
			hintMap.put("hint", "发送失败！");
		}
		JSONObject obj = new JSONObject(hintMap);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	/**
	 * 实名认证
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void IdCardVal(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(map);
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.setRealName(myMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	/**
	 * 通过用户ID获得一组通知信息
	 */
	public void getNoticeByUserID(HttpServletRequest request,HttpServletResponse response) throws FileUploadException, IOException {
		List<Map<String, Object>> list = noticeServiceImpl.getNoticeInfoByUserID(Integer.valueOf(user_id));
		Map<String, Object> noticeMap = new HashMap<String, Object>();
		noticeMap.put("noticeInfo", list);
		JSONObject json = new JSONObject(noticeMap);
		response.getWriter().print(json);
	}

}

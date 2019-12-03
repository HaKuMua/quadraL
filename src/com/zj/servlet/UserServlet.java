package com.zj.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import cn.com.util.BaseServlet;
import cn.com.util.CheckoutEmail;
import cn.com.util.CheckoutIDCard;
import cn.com.util.CheckoutPhoneNumber;
import cn.com.util.FileLoadServletUtil;

import com.alibaba.fastjson.JSON;
import com.zj.entity.User;
import com.zj.service.UserService;
import com.zj.service.impl.UserServiceImpl;
/**
 * 用户
 * @author ml
 *
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userServiceImpl = new UserService();
	private String map;
	private String callback;
	private Integer user_id;
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
		Map<String, Object> map = new HashMap<String, Object>();
		String user_phone=myMap.get("user_phone").toString();
		Integer code=new Integer(myMap.get("user_phone").toString());
		sendMap = userServiceImpl.loginByPhone(user_phone,code);
		map.put("userInfo",sendMap);
		JSONObject obj = new JSONObject(map);
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
	
	//上传头像并修改头像
	public void uploadImg(HttpServletRequest request,HttpServletResponse response) throws FileUploadException, IOException {
		// 图片上传并且返回保存的路径
		String url = FileLoadServletUtil.upload(request, response,
				"D:/quadraL/userImg/");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("src", url);
		data.put("userInfo", userServiceImpl.addUserHead(user_id, url));
		map.put("data", data);
		JSONObject obj = new JSONObject(map);
		// 如果上传成功返回1
		response.getWriter().print(obj);
	}


}

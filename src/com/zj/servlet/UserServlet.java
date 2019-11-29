package com.zj.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.com.uitl.BaseServlet;

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
	
	private Integer user_id;
	private String user_headimg_url;
	private String user_email;
	private String user_phone;
	private String user_name;
	private String user_pwd;
	private String real_name;
	private String user_IDcard;
	private String user_describe;
	public String callback;
	/**
	 * 用户名密码登录
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			User user = userServiceImpl.queryUser(user_id);
			if(user_name == user.getUser_name() && user_pwd == user.getUser_pwd()) {
				//登录成功
				map.put("msg", "登录成功");
			} else {
				//登录失败，用户名或密码错误
				map.put("msg","登录失败，用户名或密码错误");
			}
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机号登录，若手机号存在则用户注册
	 */
	public void phoneLogin(HttpServletRequest request,HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		//判断手机号是否正确
		user_phone = "15581238975";
	    String regPhone = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
	    							"|(18[0-9])|(19[8,9]))\\d{8}$";
	     Pattern pRegPhone = Pattern.compile(regPhone);
	     Matcher mRegPhone = pRegPhone.matcher(user_phone);
	     if(mRegPhone.matches()) {
	    	 //手机号正确
	    	 try {
	 			boolean bool = userServiceImpl.queryPhoneExit(user_phone);
	 			if(bool) {
	 				//手机号已存在，验证码登录
	 				
	 				map.put("msg", "手机号已存在,验证码登录");
	 			} else{
	 				//手机号不存在，直接注册
	 				int count = userServiceImpl.addUser(user_phone);
	 				if(count != 0) {
	 					//注册成功
	 					map.put("msg", "注册成功");
	 				} else {
	 					//注册失败
	 					map.put("msg", "注册失败");
	 				}
	 			}
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	     }else {
	    	 //手机号错误
	    	 map.put("msg", "手机号不存在");
	     }
		try {
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用户设置密码
	 */
	public void setUserPwd(HttpServletRequest request,HttpServletResponse response) {
		
	}
	/**
	 * 信息基本修改
	 */
	public void perfectInfo(HttpServletRequest request,HttpServletResponse response) {
		//判断信息是否符合正则表达式
		user_name = "李佳";
		real_name = "李佳";
		user_email = "1719741296@qq.com";
		user_IDcard  ="432524199902285430";
		user_phone = "15581238970";
		user_id = 1;
		user_describe = "分手快乐就发生就是减肥是登录副教授就咖啡连锁店";
	    String regName = "^([\\u4e00-\\u9fa5]){2,12}$";
	    String regRealName = "^([\\u4e00-\\u9fa5]){2,12}$";
	    String regEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$ ";
	    String regIDcard = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	    String regPhone = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
				"|(18[0-9])|(19[8,9]))\\d{8}$";
	    //
	    Map<String, String> map = new HashMap<String, String>();
	    //
	    if(user_name == null && user_name.isEmpty() && real_name == null && real_name.isEmpty() &&
	    	user_email == null && user_email.isEmpty() &&user_IDcard == null && user_IDcard.isEmpty() &&
	    	user_phone == null && user_phone.isEmpty()) {
	    	//属性存在空值
	    	map.put("msg", "属性存在空值");
	    } else {
	    	Pattern pRegName = Pattern.compile(regName);
	 	    Matcher mRegName = pRegName.matcher(user_name);
	
		    Pattern pRegRealName = Pattern.compile(regRealName);
		    Matcher mRegRealName = pRegRealName.matcher(real_name);
		    
		    Pattern pRegEmail = Pattern.compile(regEmail);
		    Matcher mRegEmail = pRegEmail.matcher(user_email);
		    
		    Pattern pRegIDcard = Pattern.compile(regIDcard);
		    Matcher mRegIDcard = pRegIDcard.matcher(user_IDcard);
		     
		    Pattern pRegPhone = Pattern.compile(regPhone);
		    Matcher mRegPhone = pRegPhone.matcher(user_phone);
		    if(mRegName.matches() && mRegRealName.matches() && mRegRealName.matches() && mRegIDcard.matches()) {
		    	//符合正则
		    	try {
		 			int count = userServiceImpl.updateUser(user_id,user_name, user_email, user_phone, real_name,user_describe,user_IDcard);
		 			if(count != 0) {
		 				//用户信息修改成功
		 				map.put("msg", "用户信息修改成功");
		 			} else {
		 				//用户信息修改失败
		 				map.put("msg", "用户信息修改失败");
		 			}
		    	} catch (SQLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		    } else {
		    	//存在属性不符合正则
		    	map.put("msg", "存在属性不符合正则");
		    }
	    } 
	    try {
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用户修改密码
	 */
	public void updateUserPwd(HttpServletRequest request,HttpServletResponse response) {
		
	}
	/**
	 * 用户上传头像
	 */
	public void addUserHead(HttpServletRequest request,HttpServletResponse response) {
		
	}
}

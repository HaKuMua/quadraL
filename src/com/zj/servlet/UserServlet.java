package com.zj.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.com.uitl.BaseServlet;
import cn.com.uitl.CheckoutEmail;
import cn.com.uitl.CheckoutIDCard;
import cn.com.uitl.CheckoutPhoneNumber;

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
	
	public String map;
	public String callback;
	@SuppressWarnings("unchecked")
	Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
	Integer user_id = Integer.valueOf(myMap.get("user_id").toString());
	String user_headimg_url = myMap.get("user_headimg_url").toString();
	String user_email = myMap.get("user_email").toString();
	String user_phone = myMap.get("user_phone").toString();
	String user_name = myMap.get("user_name").toString();
	String user_pwd = myMap.get("user_pwd").toString();
	String user_pwd1 = myMap.get("user_pwd").toString();
	String user_pwd2 = myMap.get("user_pwd2").toString();
	String real_name = myMap.get("real_name").toString();
	String user_IDcard = myMap.get("user_IDcard").toString();
	String user_describe = myMap.get("user_describe").toString();
//	private Integer user_id;
//	private String user_headimg_url;
//	private String user_email;
//	private String user_phone;
//	private String user_name;
//	private String user_pwd;
//	private String user_pwd1;
//	private String user_pwd2;
//	private String real_name;
//	private String user_IDcard;
//	private String user_describe;
	/**
	 * 邮箱/电话密码登录
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			User userInfo = userServiceImpl.queryUserInfo(user_phone, user_email, user_pwd);
			if(userInfo != null) {
				//登录成功
				map.put("msg", "登录成功");
				map.put("userInfo", userInfo);
			} else {
				//登录失败，用户名或密码错误
				map.put("msg","登录失败，用户名或密码错误");
				map.put("userInfo", null);
			}
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 手机号登录，若手机号存在则用户注册
	 */
	public void phoneLogin(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		//判断手机号是否正确
	    boolean regPhone = CheckoutPhoneNumber.isPhoneNumberValid(user_phone);
	     if(regPhone) {
	    	 //手机号正确
	    	 try {
	 			boolean bool = userServiceImpl.queryPhoneExit(user_phone);
	 			if(bool) {
	 				//手机号已存在，验证码登录
	 				
	 				map.put("msg", "手机号已存在,验证码登录");
	 			} else{
	 				//手机号不存在，直接注册
	 				User userInfo = userServiceImpl.addUser(user_phone);
	 				if(userInfo != null) {
	 					//注册成功
	 					map.put("msg", "注册成功");
	 					map.put("userInfo", userInfo);
	 				} else {
	 					//注册失败
	 					map.put("msg", "注册失败");
	 					map.put("userInfo", null);
	 				}
	 			}
	 		} catch (SQLException e) {
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
			e.printStackTrace();
		}
	}
	/**
	 * 用户设置密码
	 */
	public void setUserPwd(HttpServletRequest request,HttpServletResponse response) {
		//密码正则判断
		String regPwd = "^(\\w){6,20}$";
		Map<String, String> map = new HashMap<String, String>();
		if(user_pwd1 == null || user_pwd1.isEmpty() || user_pwd2 == null || user_pwd2.isEmpty()) {
			//密码为空
			map.put("msg", "密码为空");
		} else {
			//判断两个密码
			if(user_pwd1 == user_pwd2){
				Pattern pRegPwd = Pattern.compile(regPwd);
		 	    Matcher mRegPwd = pRegPwd.matcher(user_pwd1);
		 	    if(mRegPwd.matches()) {
		 	    	//密码正则正确
		 	    	try {
						int count = userServiceImpl.setUserPwd(user_id, user_pwd1);
						if(count != 0) {
							//密码设置成功
							map.put("msg", "密码设置成功");
						} else {
							//密码设置失败
							map.put("msg", "密码设置失败");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 	    } else {
		 	    	//密码正则错误
		 	    	map.put("msg", "密码正则错误");
		 	    }
			} else {
				//两次密码输入不一致
				map.put("msg", "两次密码输入不一致");
			}
		}
		try {
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 信息基本修改
	 */
	public void perfectInfo(HttpServletRequest request,HttpServletResponse response) {
		//正则表达式
	    String regName = "^([\\u4e00-\\u9fa5]){2,12}$";
	    String regRealName = "^([\\u4e00-\\u9fa5]){2,12}$";
	    Map<String, String> map = new HashMap<String, String>();
	    //
	    if(user_name == null || user_name.isEmpty() || real_name == null || real_name.isEmpty() ||
	    	user_email == null || user_email.isEmpty() || user_IDcard == null || user_IDcard.isEmpty() ||
	    	user_phone == null || user_phone.isEmpty()) {
	    	//属性存在空值
	    	map.put("msg", "属性存在空值");
	    } else {
	    	//判断信息是否符合正则表达式
	    	Pattern pRegName = Pattern.compile(regName);
	 	    Matcher mRegName = pRegName.matcher(user_name);
	
		    Pattern pRegRealName = Pattern.compile(regRealName);
		    Matcher mRegRealName = pRegRealName.matcher(real_name);
		    
		    boolean RegEmail = CheckoutEmail.checkEmail(user_email);
		    boolean RegIDcard = false;
			try {
				RegIDcard = CheckoutIDCard.IDCardValidate(user_IDcard);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		    boolean RegPhone = CheckoutPhoneNumber.isPhoneNumberValid(user_phone);
		    if(mRegName.matches() && mRegRealName.matches() && RegEmail && RegIDcard && RegPhone) {
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

			e.printStackTrace();
		}
	}
	/**
	 * 用户修改密码
	 */
	public void updateUserPwd(HttpServletRequest request,HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			User user = userServiceImpl.queryUserById(user_id);
			if(user_pwd.equals(user.getUser_pwd())) {
				//原密码正确
				//新密码正则判断
				String regPwd = "^(\\w){6,20}$";
				if(user_pwd1 == null || user_pwd1.isEmpty() || user_pwd2 == null || user_pwd2.isEmpty()) {
					//密码为空
					map.put("msg", "密码为空");
				} else {
					//判断两个密码
					if(user_pwd1 == user_pwd2){
						Pattern pRegPwd = Pattern.compile(regPwd);
				 	    Matcher mRegPwd = pRegPwd.matcher(user_pwd1);
				 	    if(mRegPwd.matches()) {
				 	    	//密码正则正确
				 	    	try {
								int count = userServiceImpl.setUserPwd(user_id, user_pwd1);
								if(count != 0) {
									//密码设置成功
									map.put("msg", "密码设置成功");
								} else {
									//密码设置失败
									map.put("msg", "密码设置失败");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				 	    } else {
				 	    	//密码正则错误
				 	    	map.put("msg", "密码正则错误");
				 	    }
					} else {
						//两次密码输入不一致
						map.put("msg", "两次密码输入不一致");
					}
				}
			} else {
				//原密码输入有误
				map.put("msg", "原密码输入有误");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * 用户上传头像
	 */
	public void addUserHead(HttpServletRequest request,HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int count = userServiceImpl.addUserHead(user_id, user_headimg_url);
			if(count != 0) {
				//上传成功
				map.put("msg", "头像上传成功");
			} else {
				//上传失败
				map.put("msg", "头像上传失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

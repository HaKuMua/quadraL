package com.zj.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.com.util.CheckoutEmail;
import cn.com.util.CheckoutIDCard;
import cn.com.util.CheckoutPhoneNumber;
import cn.com.util.UUIDGenerator;

import com.zj.dao.UserDao;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.User;
import com.zj.service.impl.UserServiceImpl;
/**
 * 
 * @author lijia
 *用户服务层类
 */

public class UserService implements UserServiceImpl{
	private UserDaoImpl userDaoImpl = new UserDao();
	
	/**
	 * 将所有用户信息包装成一个list<map>返回
	 */
	public List<Map<String, Object>> getAllUserInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<User> userList = userDaoImpl.getAllUserInfo();
			if(userList != null){
				list = new ArrayList<Map<String,Object>>();
				for(User user : userList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("user_id", user.getUser_id());
					map.put("user_name", user.getUser_name());
					map.put("user_headimg_url", user.getUser_headimg_url());
					map.put("user_email", user.getUser_email());
					map.put("user_phone", user.getUser_phone());
					map.put("user_IDcard", user.getUser_IDcard());
					map.put("is_lanlord", user.getIs_landlord());
					map.put("real_name", user.getReal_name());
					map.put("user_describe", user.getUser_describe());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 将单个用户信息包装成map返回
	 * 用user_phone查询
	 */
	public Map<String, Object> getUserInfoByPhone(String user_phone){
		Map<String, Object> map = null;
		try {
			User user = userDaoImpl.getUserInfoByPhone(user_phone);
			if(user != null){
				map = new HashMap<String, Object>();
				map.put("user_id", user.getUser_id());
				map.put("user_name", user.getUser_name());
				map.put("user_headimg_url", user.getUser_headimg_url());
				map.put("user_email", user.getUser_email());
				map.put("user_phone", user.getUser_phone());
				map.put("user_IDcard", user.getUser_IDcard());
				map.put("is_lanlord", user.getIs_landlord());
				map.put("real_name", user.getReal_name());
				map.put("user_describe", user.getUser_describe());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 将单个用户信息包装成map返回
	 * 用user_id查询
	 */
	public Map<String, Object> getUserInfoById(Integer user_id){
		Map<String, Object> map = null;
		try {
			User user = userDaoImpl.getUserInfoById(user_id);
			if(user != null){
				map = new HashMap<String, Object>();
				map.put("user_id", user.getUser_id());
				map.put("user_name", user.getUser_name());
				map.put("user_headimg_url", user.getUser_headimg_url());
				map.put("user_email", user.getUser_email());
				map.put("user_phone", user.getUser_phone());
				map.put("user_IDcard", user.getUser_IDcard());
				map.put("is_lanlord", user.getIs_landlord());
				map.put("real_name", user.getReal_name());
				map.put("user_describe", user.getUser_describe());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加用户
	 * 返回user
	 */
	public int addUser(String user_phone) throws SQLException {
		//自动生成user_name
		String user_name = UUIDGenerator.getUUID().substring(0,8);
		int count = userDaoImpl.addUser(user_name, user_phone);
		return count;
	}
	/**
	 * 修改用户
	 */
	public int updateUser(Integer user_id, String user_name,
			String user_email, String user_phone,
			String user_describe) throws SQLException {
		return userDaoImpl.updateUser(user_id, user_name, user_email, user_phone, user_describe);
	}
	/**
	 * 通过id查询用户
	 * @throws SQLException 
	 */
	public User queryUserById(Integer user_id) throws SQLException {
		return userDaoImpl.queryUserById(user_id);
	}
	
	/**
	 * 查询手机号是否存在
	 * @throws SQLException 
	 */
	public boolean queryPhoneExit(String user_phone) throws SQLException {
		boolean bool = true;
		Integer count = userDaoImpl.queryPhoneExit(user_phone);
		if(count == null) {
			bool = false;
		}
		return bool;
	}
	/**
	 * 用户设置密码
	 * @throws SQLException 
	 */
	public int setUserPwd(Integer user_id, String user_pwd) throws SQLException {
		return userDaoImpl.setUserPwd(user_id, user_pwd);
	}
	/**
	 * 用户修改密码
	 * @throws SQLException 
	 */
	public int updateUserPwd(Integer user_id, String user_pwd)
			throws SQLException {
		return userDaoImpl.updateUserPwd(user_id, user_pwd);
	}
	/**
	 * 用户上传头像
	 * @throws SQLException 
	 */
	public int addUserHead(Integer user_id, String user_headimg_url)
			throws SQLException {
		return userDaoImpl.addUserHead(user_id, user_headimg_url);
	}

	/**
	 * 添加一个用户信息
	 * @param map
	 * @return
	 */
//	public String addUserInfo(Map<String, Object> map){
//		User user = new User();
//		if(!CheckoutEmail.checkEmail(map.get("user_email").toString())){
//			return "邮箱有误";
//		}
//		try {
//			if(!CheckoutIDCard.IDCardValidate(map.get("user_IDcard").toString())){
//				return "身份证有误";
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		if(!CheckoutPhoneNumber.isPhoneNumberValid(map.get("user_phone").toString())){
//			return "手机号有误";
//		}
//		try{
//		user.setUser_describe(map.get("user_describe").toString());
//		user.setUser_email(map.get("user_email").toString());
//		user.setUser_headimg_url(map.get("user_headimg_url").toString());
//		user.setUser_id(Integer.valueOf((String) map.get("is_landlord")) );
//		user.setUser_IDcard(map.get("user_IDcard").toString());
//		user.setUser_name(map.get("user_name").toString());
//		user.setUser_phone( map.get("user_phone").toString());
//		user.setReal_name( map.get("real_name").toString());
//		user.setUser_pwd( map.get("user_pwd").toString());
//		}catch (Exception e) {
//			e.printStackTrace();
//			return "输入有误";
//		}
//		try {
//			if(userDaoImpl.addUserInfo(user) > 0){
//				return "插入成功";
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//			return map;
//		}
//		return "插入失败";
//	}
	/**
	 * 根据电话/email和密码查询用户
	 * @throws SQLException 
	 */
	public User queryUserInfo(String user_phone, String user_email,
			String user_pwd) throws SQLException {
		User user = null;
		if(user_phone != null && user_email == null) {
			user_email = "";
			user = userDaoImpl.queryUserInfo(user_phone, user_email, user_pwd);
		} else {
			user_phone = "";
			user = userDaoImpl.queryUserInfo(user_phone, user_email, user_pwd);
		}
		return user;
	}
	/**
	 * 手机号/邮箱和密码登录
	 */
	public Map<String, Object> login(Map<String, Object> loginInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//登录所需数据
		String user_phone = null;
		String user_pwd = null;
		String user_email = null;
		if(loginInfo.get("user_phone") != null)
		user_phone = loginInfo.get("user_phone").toString();
		if(loginInfo.get("user_pwd") != null)
		user_pwd = loginInfo.get("user_pwd").toString();
		if(loginInfo.get("user_email") != null)
		user_email = loginInfo.get("user_email").toString();
		
		try {
			User userInfo = this.queryUserInfo(user_phone, user_email, user_pwd);
			if(userInfo != null) {
				//登录成功
				map.put("msg", "登录成功");
				map.put("user_id", userInfo.getUser_id());
				map.put("user_name", userInfo.getUser_name());
				map.put("user_headimg_url", userInfo.getUser_headimg_url());
				map.put("user_email", userInfo.getUser_email());
				map.put("user_phone", userInfo.getUser_phone());
				map.put("user_IDcard", userInfo.getUser_IDcard());
				map.put("is_landlord", userInfo.getIs_landlord());
				map.put("user_pwd", userInfo.getUser_pwd());
				map.put("money", userInfo.getMoney());
				map.put("real_name", userInfo.getReal_name());
				map.put("user_describe", userInfo.getUser_describe());
				map.put("inform_date", userInfo.getInform_date());
			} else {
				//登录失败
				map.put("msg", "密码错误");
			}
		} catch (SQLException e) {
			map.put("msg", "数据查询异常");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 手机号登录，若手机号存在则用户注册
	 */
	public Map<String, Object> phoneLogin(Map<String, Object> phoneLoginInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String user_phone = null;
		if(phoneLoginInfo.get("user_phone") != null)
		user_phone = phoneLoginInfo.get("user_phone").toString();
		   boolean regPhone = CheckoutPhoneNumber.isPhoneNumberValid(user_phone);
		   if(regPhone) {
			   //手机号正确
			   try {
			    	boolean bool = this.queryPhoneExit(user_phone);
			 		if(bool) {
			 			//手机号已存在，验证码登录
			 			
			 				
			 				
			 		} else{
			 			//手机号不存在，直接注册
			 			int count = this.addUser(user_phone);
			 			if(count != 0) {
			 				//注册成功
			 				User userInfo = this.queryUserByPhone(user_phone);
							map.put("msg", "注册成功");
							map.put("user_id", userInfo.getUser_id());
							map.put("user_name", userInfo.getUser_name());
							map.put("user_headimg_url", userInfo.getUser_headimg_url());
							map.put("user_email", userInfo.getUser_email());
							map.put("user_phone", userInfo.getUser_phone());
							map.put("user_IDcard", userInfo.getUser_IDcard());
							map.put("is_landlord", userInfo.getIs_landlord());
							map.put("user_pwd", userInfo.getUser_pwd());
							map.put("money", userInfo.getMoney());
							map.put("real_name", userInfo.getReal_name());
							map.put("user_describe", userInfo.getUser_describe());
							map.put("inform_date", userInfo.getInform_date());
			 			} else {
			 				//注册失败
			 				map.put("msg", "注册失败");
			 			}
			 		} 
			 	} catch (SQLException e) {
			 		map.put("msg", "数据查询异常");
			 		e.printStackTrace();
			 	} 
		   } else {
			   //手机号错误
		    	map.put("msg", "手机号错误");
		   }
		return map;
	}
	/**
	 * 用户设置密码
	 */
	public Map<String, Object> setUserPwd(Map<String, Object> setUserPwdInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取原密码与两个新密码和user_id
		Integer user_id = null;
		if(setUserPwdInfo.get("user_id") != null)
		user_id = Integer.valueOf(setUserPwdInfo.get("user_id").toString());
		
		String new_user_pwd1 = null;
		if(setUserPwdInfo.get("new_user_pwd1") != null)
		new_user_pwd1 = setUserPwdInfo.get("new_user_pwd1").toString();
		
		String new_user_pwd2 = null;
		if(setUserPwdInfo.get("new_user_pwd2") != null)
		new_user_pwd2 = setUserPwdInfo.get("new_user_pwd2").toString();
		
		//密码正则判断
		String regPwd = "^(\\w){6,20}$";
		if(new_user_pwd1 == null || new_user_pwd1.isEmpty() || new_user_pwd2 == null || new_user_pwd2.isEmpty()) {
			//密码为空
			map.put("msg", "密码不能为空");
		} else {
			//判断两个密码
			if(new_user_pwd1.equals(new_user_pwd2)){
				Pattern pRegPwd = Pattern.compile(regPwd);
				Matcher mRegPwd = pRegPwd.matcher(new_user_pwd1);
				 if(mRegPwd.matches()) {
					 //密码正则正确
				 	 try {
				 		 int count = this.setUserPwd(user_id, new_user_pwd1);
				 	  	 if(count != 0) {
				 	  		 //密码设置成功
				 	  		 map.put("msg", "密码设置成功");
						} else {
							//密码设置失败
							map.put("msg", "密码设置失败");
						}
					} catch (SQLException e) {
						map.put("msg", "数据查询异常");
						e.printStackTrace();
					}
				  } else {
					  //密码正则错误
					  map.put("msg", "信息输入有误");
				  }
				} else {
					//两次密码输入不一致
					map.put("msg", "两次密码输入不一致");
				}
			}
		return map;
	}
	/**
	 * 修改基本信息
	 */
	public Map<String, Object> updateBasicInfo(Map<String, Object> setUserInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取所有基本信息
		String user_name = null;
		String user_email = null;
		String user_phone = null;
		Integer user_id = null;
		String user_describe = null;
		if(setUserInfo.get("user_name") != null)
			user_name = setUserInfo.get("user_name").toString();
		if(setUserInfo.get("user_email") != null)
			user_email = setUserInfo.get("user_email").toString();
		if(setUserInfo.get("user_phone") != null)
			user_phone = setUserInfo.get("user_phone").toString();
		if(setUserInfo.get("user_id") != null)
			user_id = Integer.valueOf(setUserInfo.get("user_id").toString());
		if(setUserInfo.get("user_describe") != null)
			user_describe = setUserInfo.get("user_describe").toString();
		//正则表达式
	    String regName = "^([\\u4e00-\\u9fa5]){2,12}$";
	    //
	    if(user_name == null || user_name.isEmpty() || user_email == null || user_email.isEmpty() ||
	    	user_phone == null || user_phone.isEmpty()) {
	    	//属性存在空值
	    	map.put("msg", "不能存在空值");
	    } else {
	    	//判断信息是否符合正则表达式
	    	Pattern pRegName = Pattern.compile(regName);
	 	    Matcher mRegName = pRegName.matcher(user_name);
		    
		    boolean RegEmail = CheckoutEmail.checkEmail(user_email);
		    boolean RegPhone = CheckoutPhoneNumber.isPhoneNumberValid(user_phone);
		    if(mRegName.matches() && RegEmail && RegPhone) {
		    	//符合正则
		    	try {
		 			int count = this.updateUser(user_id,user_name, user_email, user_phone,user_describe);
		 			if(count != 0) {
		 				//用户信息修改成功
		 				map.put("msg", "用户信息修改成功");
		 			} else {
		 				//用户信息修改失败
		 				map.put("msg", "用户信息修改失败");
		 			}
		    	} catch (SQLException e) {
		    		map.put("msg", "数据查询异常");
		 			e.printStackTrace();
		 		}
		    } else {
		    	//存在属性不符合正则
		    	map.put("msg", "信息输入有误");
		    }
	    } 
		return map;
	}
	/**
	 * 修改密码
	 */
	public Map<String, Object> updateUserPwd(
			Map<String, Object> updateUserPwdInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取所有基本信息
		//获取修改密码所需信息
		Integer user_id = null;
		if(updateUserPwdInfo.get("user_id") != null)
			user_id = Integer.valueOf(updateUserPwdInfo.get("user_id").toString());
		
		String user_pwd = null;
		if(updateUserPwdInfo.get("user_pwd") != null)
			user_pwd = updateUserPwdInfo.get("user_pwd").toString();
		
		String new_user_pwd1 = null;
		if(updateUserPwdInfo.get("new_user_pwd1") != null)
			new_user_pwd1 = updateUserPwdInfo.get("new_user_pwd1").toString();
		
		String new_user_pwd2 = null;
		if(updateUserPwdInfo.get("new_user_pwd2") != null)
			new_user_pwd2 = updateUserPwdInfo.get("new_user_pwd2").toString();
		try {
			User user = this.queryUserById(user_id);
			if(user_pwd.equals(user.getUser_pwd())) {
				//原密码正确
				//新密码正则判断
				String regPwd = "^(\\w){6,20}$";
				if(new_user_pwd1 == null || new_user_pwd1.isEmpty() || new_user_pwd2 == null || new_user_pwd2.isEmpty()) {
					//密码为空
					map.put("msg", "密码不能为空");
				} else {
					//判断两个密码是否一致
					if(new_user_pwd1.equals(new_user_pwd2)){
						Pattern pRegPwd = Pattern.compile(regPwd);
				 	    Matcher mRegPwd = pRegPwd.matcher(new_user_pwd1);
				 	    if(mRegPwd.matches()) {
				 	    	//密码正则正确
				 	    	try {
								int count = this.setUserPwd(user_id, new_user_pwd1);
								if(count != 0) {
									//密码设置成功
									map.put("msg", "密码设置成功");
								} else {
									//密码设置失败
									map.put("msg", "密码设置失败");
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
				 	    } else {
				 	    	//密码正则错误
				 	    	map.put("msg", "信息输入有误,密码为6-20位");
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
			map.put("msg", "数据查询异常");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 用户上传头像
	 */
	public Map<String, Object> addUserHead(Map<String, Object> addUserHeadInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer user_id = null;
		if(addUserHeadInfo.get("user_id") != null)
			user_id = Integer.valueOf(addUserHeadInfo.get("user_id").toString());
		
		String user_headimg_url = null;
		if(addUserHeadInfo.get("user_headimg_url") != null)
			user_headimg_url = addUserHeadInfo.get("user_headimg_url").toString();
		try {
			int count = this.addUserHead(user_id, user_headimg_url);
			if(count != 0) {
				//上传成功
				map.put("msg", "头像上传成功");
			} else {
				//上传失败
				map.put("msg", "头像上传失败");
			}
		} catch (SQLException e) {
			map.put("msg", "数据查询异常");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 通过手机号获取用户
	 * @throws SQLException 
	 */
	public User queryUserByPhone(String user_phone) throws SQLException {
		return userDaoImpl.getUserInfoByPhone(user_phone);
	}
	/**
	 *上传身份证和真实姓名
	 * @throws SQLException 
	 */
	public Map<String, Object> realNameInfo(Map<String, Object> realNameInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取实名认证所需信息
		Integer user_id = null;
		if(realNameInfo.get("user_id") != null)
			user_id = Integer.valueOf(realNameInfo.get("user_id").toString());
		String user_IDcard = null;
		if(realNameInfo.get("user_IDcard") != null)
			user_IDcard = realNameInfo.get("user_IDcard").toString();
		String real_name = null;
		if(realNameInfo.get("real_name") != null)
			real_name = realNameInfo.get("real_name").toString();
		//正则验证
		String regReal_name = "^([\\u4e00-\\u9fa5]){2,12}$";
		Pattern pReal_name = Pattern.compile(regReal_name);
 	    Matcher mReal_name = pReal_name.matcher(real_name);
		boolean bool = false;
		try {
			bool = CheckoutIDCard.IDCardValidate(user_IDcard);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bool && mReal_name.matches()) {
			int count;
			try {
				count = userDaoImpl.realNameInfo(user_IDcard, user_id, real_name);
				if(count != 0) {
					//实名认证成功
					map.put("msg", "实名认证成功");
				} else {
					//实名认证失败
					map.put("msg", "实名认证失败");
				}
			} catch (SQLException e) {
				map.put("msg", "数据插入异常");
				e.printStackTrace();
			}
		} else {
			//正则失败
			map.put("msg", "信息输入有误");
		}
		return map;
	}
}

package com.zj.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

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
 * @author lijia 用户服务层类
 */

public class UserService implements UserServiceImpl {
	private UserDaoImpl userDaoImpl = new UserDao();
	private Logger log = Logger.getLogger(UserService.class);
	/**
	 * 将所有用户信息包装成一个list<map>返回
	 */
	public List<Map<String, Object>> getAllUserInfo() {
		List<Map<String, Object>> list = null;
		try {
			List<User> userList = userDaoImpl.getAllUserInfo();
			if (userList != null) {
				list = new ArrayList<Map<String, Object>>();
				for (User user : userList) {
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
	 * 将单个用户信息包装成map返回 用user_phone查询
	 */
	public Map<String, Object> getUserInfoByPhone(String user_phone) {
		Map<String, Object> map = null;
		try {
			User user = userDaoImpl.getUserInfoByPhone(user_phone);
			if (user != null) {
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
	 * 将单个用户信息包装成map返回 用user_id查询
	 */
	public Map<String, Object> getUserInfoById(Integer user_id) {
		Map<String, Object> map = null;
		try {
			User user = userDaoImpl.getUserInfoById(user_id);
			if (user != null) {
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
				map.put("money", user.getMoney());
				map.put("inform_date", user.getInform_date());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 手机号/邮箱和密码登录
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> loginByCode(Map<String, Object> info) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 登录所需数据
		String user_code = info.get("user_code").toString();
		String user_pwd = info.get("user_pwd").toString();
		User userInfo;
		try {
			userInfo = userDaoImpl.queryUserInfo(user_code, user_pwd);
			if (userInfo != null) {
				// 登录成功
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("user_id", userInfo.getUser_id());
				userMap.put("user_name", userInfo.getUser_name());
				userMap.put("user_headimg_url", userInfo.getUser_headimg_url());
				userMap.put("user_email", userInfo.getUser_email());
				userMap.put("user_phone", userInfo.getUser_phone());
				userMap.put("user_IDcard", userInfo.getUser_IDcard());
				userMap.put("is_landlord", userInfo.getIs_landlord());
				userMap.put("user_pwd", userInfo.getUser_pwd());
				userMap.put("money", userInfo.getMoney());
				userMap.put("real_name", userInfo.getReal_name());
				userMap.put("user_describe", userInfo.getUser_describe());
				userMap.put("inform_date", userInfo.getInform_date());
				map.put("userInfo", userMap);
				map.put("msg", "登录成功！");
			} else {
				// 登录失败
				map.put("msg", "账号或密码错误，登录失败！");
			}
		} catch (SQLException e) {
			// 登录失败
			map.put("msg", "登录失败！");
			return map;
		}
		return map;
	}

	/**
	 * 手机号登录，若手机号存在则用户注册
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> loginByPhone(String user_phone, Integer code) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean bool = CheckoutPhoneNumber.isPhoneNumberValid(user_phone);
		if (bool) {// 手机号格式正确
			Integer count;
			try {
				count = userDaoImpl.queryPhoneExit(user_phone);
				User userInfo = null;
				if (count > 0) {
					// 手机号已存在，验证码登录
					Integer myCode = 1234;// 真实验证码
					if (code != myCode) {
						map.put("msg", "验证码不正确，登录失败！");
						return map;
					} else {
						map.put("msg", "登录成功！");
						userInfo = userDaoImpl.getUserInfoByPhone(user_phone);
					}
				} else {
					// 手机号不存在，直接注册
					String user_name = UUIDGenerator.getUUID().substring(0, 7);
					count = userDaoImpl.addUser(user_name, user_phone);
					if (count < 0) {// 注册成功
						// 注册失败
						map.put("msg", "注册失败！");
						return map;
					} else {
						map.put("msg", "注册成功！");
						userInfo = userDaoImpl.getUserInfoByPhoneAndName(
								user_name, user_phone);
					}
				}
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("user_id", userInfo.getUser_id());
				userMap.put("user_name", userInfo.getUser_name());
				userMap.put("user_headimg_url", userInfo.getUser_headimg_url());
				userMap.put("user_email", userInfo.getUser_email());
				userMap.put("user_phone", userInfo.getUser_phone());
				userMap.put("user_IDcard", userInfo.getUser_IDcard());
				userMap.put("is_landlord", userInfo.getIs_landlord());
				userMap.put("user_pwd", userInfo.getUser_pwd());
				userMap.put("money", userInfo.getMoney());
				userMap.put("real_name", userInfo.getReal_name());
				userMap.put("user_describe", userInfo.getUser_describe());
				userMap.put("inform_date", userInfo.getInform_date());
				map.put("userInfo", userMap);
			} catch (Exception e) {
				map.put("msg", "注册失败！");
				return map;
			}
		} else {
			// 手机号错误
			map.put("msg", "手机号格式错误，登录失败！");
		}
		return map;
	}

	/**
	 * 用户设置密码
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> setUserPwd(Map<String, Object> info) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer user_id = Integer.valueOf(info.get("user_id").toString());
		String user_pwd = info.get("new_user_pwd1").toString();
		String user_pwdVal = info.get("new_user_pwd2").toString();
		// 密码正则判断
		String regPwd = "^(\\w){6,20}$";
		if (user_pwd.equals(user_pwdVal) && user_pwd != null) {// 判断两个密码是否相等
			Pattern pRegPwd = Pattern.compile(regPwd);
			Matcher mRegPwd = pRegPwd.matcher(user_pwd);
			if (mRegPwd.matches()) {
				// 密码正则正确
				int count;
				try {
					count = userDaoImpl.updateUserPwd(user_id, user_pwdVal);
					if (count != 0) {
						// 密码设置成功
						map.put("msg", "密码设置成功");
						User userInfo = userDaoImpl.getUserInfoById(user_id);
						Map<String, Object> userMap = new HashMap<String, Object>();
						userMap.put("user_id", userInfo.getUser_id());
						userMap.put("user_name", userInfo.getUser_name());
						userMap.put("user_headimg_url",
								userInfo.getUser_headimg_url());
						userMap.put("user_email", userInfo.getUser_email());
						userMap.put("user_phone", userInfo.getUser_phone());
						userMap.put("user_IDcard", userInfo.getUser_IDcard());
						userMap.put("is_landlord", userInfo.getIs_landlord());
						userMap.put("user_pwd", userInfo.getUser_pwd());
						userMap.put("money", userInfo.getMoney());
						userMap.put("real_name", userInfo.getReal_name());
						userMap.put("user_describe",
								userInfo.getUser_describe());
						userMap.put("inform_date", userInfo.getInform_date());
						map.put("userInfo", userMap);
					} else {
						// 密码设置失败
						map.put("msg", "密码设置失败");
					}
				} catch (Exception e) {
					map.put("msg", "密码设置失败");
					return map;
				}

			} else {
				// 密码正则错误
				map.put("msg", "密码格式错误，修改失败！");
			}
		} else {
			// 两次密码输入不一致
			map.put("msg", "两次密码输入不一致");
		}
		return map;
	}

	/**
	 * 修改基本信息
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> updateBasicInfo(Map<String, Object> info) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取所有基本信息
		Integer user_id = new Integer(info.get("user_id").toString());
		String user_name = info.get("user_name").toString();
		String user_email = info.get("user_email").toString();
		String user_describe = info.get("user_describe").toString();
		// 正则表达式
		String regName = "^([\\u4e00-\\u9fa5]){2,12}$";
		String regRealName = "^([\\u4e00-\\u9fa5]){2,12}$";
		if (user_name == null || user_name.isEmpty() || user_email == null
				|| user_email.isEmpty()) {
			// 属性存在空值
			map.put("msg", "属性存在空值");
		} else {
			// 判断信息是否符合正则表达式
			Pattern pRegName = Pattern.compile(regName);
			Matcher mRegName = pRegName.matcher(user_name);
			boolean RegEmail = CheckoutEmail.checkEmail(user_email);
			boolean RegIDcard = false;
			if (mRegName.matches() && RegEmail && RegIDcard) {// 符合正则
				int count = -1;
				try {
					count = userDaoImpl.updateBasicInfo(user_id, user_name,
							user_email, user_describe);
					if (count > 0) {
						// 用户信息修改成功
						map.put("msg", "用户信息修改成功");
						User userInfo = userDaoImpl.getUserInfoById(user_id);
						Map<String, Object> userMap = new HashMap<String, Object>();
						userMap.put("user_id", userInfo.getUser_id());
						userMap.put("user_name", userInfo.getUser_name());
						userMap.put("user_headimg_url",
								userInfo.getUser_headimg_url());
						userMap.put("user_email", userInfo.getUser_email());
						userMap.put("user_phone", userInfo.getUser_phone());
						userMap.put("user_IDcard", userInfo.getUser_IDcard());
						userMap.put("is_landlord", userInfo.getIs_landlord());
						userMap.put("user_pwd", userInfo.getUser_pwd());
						userMap.put("money", userInfo.getMoney());
						userMap.put("real_name", userInfo.getReal_name());
						userMap.put("user_describe",
								userInfo.getUser_describe());
						userMap.put("inform_date", userInfo.getInform_date());
						map.put("userInfo", userMap);
					} else {
						// 用户信息修改失败
						map.put("msg", "用户信息修改失败");
					}
				} catch (Exception e) {
					map.put("msg", "用户信息修改失败");
					return map;
				}
			} else {
				// 存在属性不符合正则
				map.put("msg", "存在属性不符合正则");
			}
		}
		return map;
	}

	/**
	 * 修改密码
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> updateUserPwd(Map<String, Object> info) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取所有基本信息
		// 获取修改密码所需信息
		Integer user_id = Integer.valueOf(info.get("user_id").toString());
		String user_pwd = info.get("user_pwd").toString();
		String user_pwd1 = info.get("user_pwd1").toString();
		String user_pwd2 = info.get("user_pwd2").toString();
		User userInfo;
		try {
			userInfo = userDaoImpl.getUserInfoById(user_id);
			if (user_pwd.equals(userInfo.getUser_pwd())) {// 原密码正确
				// 新密码正则判断
				String regPwd = "^(\\w){6,20}$";
				if (user_pwd1 == null || user_pwd1.isEmpty()
						|| user_pwd2 == null || user_pwd2.isEmpty()) {// 密码为空
					map.put("msg", "密码为空");
				} else {
					// 判断两个密码是否一致
					if (user_pwd1.equals(user_pwd2)) {
						Pattern pRegPwd = Pattern.compile(regPwd);
						Matcher mRegPwd = pRegPwd.matcher(user_pwd1);
						if (mRegPwd.matches()) {
							// 密码正则正确
							int count = userDaoImpl.updateUserPwd(user_id,
									user_pwd1);
							if (count != 0) {
								// 密码设置成功
								Map<String, Object> userMap = new HashMap<String, Object>();
								userMap.put("user_id", userInfo.getUser_id());
								userMap.put("user_name",
										userInfo.getUser_name());
								userMap.put("user_headimg_url",
										userInfo.getUser_headimg_url());
								userMap.put("user_email",
										userInfo.getUser_email());
								userMap.put("user_phone",
										userInfo.getUser_phone());
								userMap.put("user_IDcard",
										userInfo.getUser_IDcard());
								userMap.put("is_landlord",
										userInfo.getIs_landlord());
								userMap.put("user_pwd", userInfo.getUser_pwd());
								userMap.put("money", userInfo.getMoney());
								userMap.put("real_name",
										userInfo.getReal_name());
								userMap.put("user_describe",
										userInfo.getUser_describe());
								userMap.put("inform_date",
										userInfo.getInform_date());
								map.put("userInfo", userMap);
								map.put("msg", "密码修改成功");
							} else {
								// 密码设置失败
								map.put("msg", "密码修改失败");
							}
						} else {
							// 密码正则错误
							map.put("msg", "正则错误，密码修改失败");
						}
					} else {
						// 两次密码输入不一致
						map.put("msg", "两次密码输入不一致，修改失败");
					}
				}
			} else {
				// 原密码输入有误
				map.put("msg", "原密码输入有误，修改失败");
			}
		} catch (SQLException e) {
			map.put("msg", "修改失败");
			return map;
		}

		return map;
	}

	/**
	 * 用户上传头像
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> addUserHead(Integer user_id,
			String user_headimg_url) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count;
		try {
			count = userDaoImpl.addUserHead(user_id, user_headimg_url);
			System.out.println(count);
			if (count > 0) {
				// 上传成功
				map.put("msg", "头像上传成功");
				User userInfo = userDaoImpl.getUserInfoById(user_id);
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("user_id", userInfo.getUser_id());
				userMap.put("user_name", userInfo.getUser_name());
				userMap.put("user_headimg_url", userInfo.getUser_headimg_url());
				userMap.put("user_email", userInfo.getUser_email());
				userMap.put("user_phone", userInfo.getUser_phone());
				userMap.put("user_IDcard", userInfo.getUser_IDcard());
				userMap.put("is_landlord", userInfo.getIs_landlord());
				userMap.put("user_pwd", userInfo.getUser_pwd());
				userMap.put("money", userInfo.getMoney());
				userMap.put("real_name", userInfo.getReal_name());
				userMap.put("user_describe", userInfo.getUser_describe());
				userMap.put("inform_date", userInfo.getInform_date());
				map.put("userInfo", userMap);
			} else {
				// 上传失败
				map.put("msg", "头像上传失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("msg", "未知原因，头像上传失败!");
			return map;
		}
		return map;
	}

	/**
	 * 实名认证
	 * 
	 * @throws SQLException
	 */
	public Map<String, Object> setRealName(Map<String, Object> info) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 认证所需数据
		Integer user_id = new Integer(info.get("user_id").toString());
		String real_name = info.get("real_name").toString();
		String user_IDcard = info.get("user_IDcard").toString();
		Integer count = -1;
		try {
			count = userDaoImpl.setRealName(user_id, real_name, user_IDcard);
		} catch (Exception e) {
			// 认证失败
			map.put("msg", "认证失败！");
			return map;
		}
		if (count > 0) {
			// 认证成功
			User userInfo = null;
			try {
				userInfo = userDaoImpl.getUserInfoById(user_id);
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("user_id", userInfo.getUser_id());
				userMap.put("user_name", userInfo.getUser_name());
				userMap.put("user_headimg_url", userInfo.getUser_headimg_url());
				userMap.put("user_email", userInfo.getUser_email());
				userMap.put("user_phone", userInfo.getUser_phone());
				userMap.put("user_IDcard", userInfo.getUser_IDcard());
				userMap.put("is_landlord", userInfo.getIs_landlord());
				userMap.put("user_pwd", userInfo.getUser_pwd());
				userMap.put("money", userInfo.getMoney());
				userMap.put("real_name", userInfo.getReal_name());
				userMap.put("user_describe", userInfo.getUser_describe());
				userMap.put("inform_date", userInfo.getInform_date());
				map.put("userInfo", userMap);
				map.put("msg", "认证成功！");
			} catch (SQLException e) {
				// 认证失败
				map.put("msg", "认证失败！");
				return map;
			}
		} else {
			// 认证失败
			map.put("msg", "认证失败！");
		}
		return map;
	}
	/**
	 *通过用户ID返回单个用户信息 
	 * @param user_id
	 * @return
	 */
	public Map<String, Object> getUserInfoByUserID(Integer user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = userDaoImpl.getUserInfoById(user_id);
			map.put("user_id", user.getUser_id());
			map.put("user_name", user.getUser_name());
			map.put("user_headimg_url", user.getUser_headimg_url());
			map.put("user_email", user.getUser_email());
			map.put("user_phone", user.getUser_phone());
			map.put("user_IDcard", user.getUser_IDcard());
			map.put("is_landlord", user.getIs_landlord());
			map.put("user_pwd", user.getUser_pwd());
			map.put("money", user.getMoney());
			map.put("real_name", user.getReal_name());
			map.put("user_describe", user.getUser_describe());
			map.put("inform_date", user.getInform_date());
		} catch (SQLException e) {
			log.error("数据库操作异常");
		}
		return map;
	}
	/**
	 * 用户充值
	 */
	public Integer topUp(Double price, Integer user_id) {
		Integer data = null;
		try {
			if(price > 0)
				data = userDaoImpl.topUp(price, user_id);
			else
				return -1;
		} catch (SQLException e) {
			log.error("数据库操作异常");
			return -1;
		}
		return data > 0 ? 1 : -1;
	}

}

package com.zj.entity;

import java.io.Serializable;

/**
 * 管理员实体类
 * @author lijia
 *
 */
public class ManagerInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String manager_name;
	private String manager_password;
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	@Override
	public String toString() {
		return "ManagerInfo [manager_name=" + manager_name
				+ ", manager_password=" + manager_password + "]";
	}
}

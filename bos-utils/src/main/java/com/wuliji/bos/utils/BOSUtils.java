package com.wuliji.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wuliji.bos.entity.User;

/**
 * bos项目的工具类
 * @author Administrator
 *
 */
public class BOSUtils {
	
	/**
	 * 获取session对象
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 获取登录对象
	 */
	public static User getLoginUser() {
		return (User) getSession().getAttribute("user");
	}
}

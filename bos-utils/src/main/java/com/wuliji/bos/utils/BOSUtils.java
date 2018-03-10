package com.wuliji.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wuliji.bos.entity.User;

/**
 * bos��Ŀ�Ĺ�����
 * @author Administrator
 *
 */
public class BOSUtils {
	
	/**
	 * ��ȡsession����
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * ��ȡ��¼����
	 */
	public static User getLoginUser() {
		return (User) getSession().getAttribute("user");
	}
}

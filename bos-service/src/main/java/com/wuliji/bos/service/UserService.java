package com.wuliji.bos.service;

import com.wuliji.bos.entity.User;

public interface UserService {
	
	/**
	 * �û���¼
	 * @param model
	 * @return
	 */
	User login(User model);
	
	/**
	 * �޸�����
	 * @param id
	 * @param password
	 */
	void editPassWord(String id, String password);

}

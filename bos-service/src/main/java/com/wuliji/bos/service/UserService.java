package com.wuliji.bos.service;

import com.wuliji.bos.entity.User;

public interface UserService {
	
	/**
	 * ÓÃ»§µÇÂ¼
	 * @param model
	 * @return
	 */
	User login(User model);
	
	/**
	 * ĞŞ¸ÄÃÜÂë
	 * @param id
	 * @param password
	 */
	void editPassWord(String id, String password);

}

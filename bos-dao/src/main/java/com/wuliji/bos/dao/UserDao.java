package com.wuliji.bos.dao;

import com.wuliji.bos.dao.base.IBaseDao;
import com.wuliji.bos.entity.User;

public interface UserDao extends IBaseDao<User> {
	
	User findUserByUsernameAndPassword(String username, String password);
	
}

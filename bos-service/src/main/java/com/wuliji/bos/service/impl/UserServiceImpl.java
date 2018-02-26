package com.wuliji.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuliji.bos.dao.UserDao;
import com.wuliji.bos.entity.User;
import com.wuliji.bos.service.UserService;
import com.wuliji.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		// π”√MD5º”√‹√‹¬Î
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(), password);
	}
	
}

package com.wuliji.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuliji.bos.dao.UserDao;
import com.wuliji.bos.dao.base.impl.IBaseDaoImpl;
import com.wuliji.bos.entity.User;

@Repository
public class UserDaoImpl extends IBaseDaoImpl<User> implements UserDao {

	@Override
	/**
	 * 根据用户名密码查询用户
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = "from User u where u.username = ? and u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
}

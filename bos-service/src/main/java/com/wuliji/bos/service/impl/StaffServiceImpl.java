package com.wuliji.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuliji.bos.dao.StaffDao;
import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.service.StaffService;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao staffDao;
	
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}
	
}

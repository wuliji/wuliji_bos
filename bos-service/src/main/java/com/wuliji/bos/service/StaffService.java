package com.wuliji.bos.service;

import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.utils.PageBean;

public interface StaffService {
	
	/**
	 * ±£¥Êstaff
	 * @param model
	 */
	public void save(Staff model);
	
	/**
	 * ∑÷“≥≤È—Ø
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
}

package com.wuliji.bos.service;

import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.utils.PageBean;

public interface StaffService {
	
	/**
	 * ����staff
	 * @param model
	 */
	public void save(Staff model);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
}

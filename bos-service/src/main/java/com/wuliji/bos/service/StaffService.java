package com.wuliji.bos.service;

import java.util.List;

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

	/**
	 * ����ɾ��ȡ��Ա
	 * @param ids
	 */
	public void deleteBatch(String ids);
	
	/**
	 * ����id��ѯ
	 * @param id
	 */
	public Staff findById(String id);
	
	/**
	 * ����ȡ��Ա
	 * @param staff
	 */
	public void update(Staff staff);
	
	/**
	 * ��ѯδɾ����ȡ��Ա
	 * @return
	 */
	public List<Staff> findListIsNotDelete();
	
}

package com.wuliji.bos.service;

import java.util.List;

import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.utils.PageBean;

public interface StaffService {
	
	/**
	 * 保存staff
	 * @param model
	 */
	public void save(Staff model);
	
	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);

	/**
	 * 批量删除取派员
	 * @param ids
	 */
	public void deleteBatch(String ids);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public Staff findById(String id);
	
	/**
	 * 更新取派员
	 * @param staff
	 */
	public void update(Staff staff);
	
	/**
	 * 查询未删除的取派员
	 * @return
	 */
	public List<Staff> findListIsNotDelete();
	
}

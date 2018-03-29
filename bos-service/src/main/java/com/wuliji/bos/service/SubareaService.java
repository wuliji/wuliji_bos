package com.wuliji.bos.service;

import java.util.List;

import com.wuliji.bos.entity.Subarea;
import com.wuliji.bos.utils.PageBean;

public interface SubareaService {
	
	/**
	 * 保存分区
	 * @param model
	 */
	void save(Subarea model);
	
	/**
	 * 分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<Subarea> findAll();
	
}

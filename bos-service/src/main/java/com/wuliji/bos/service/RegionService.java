package com.wuliji.bos.service;

import java.util.List;

import com.wuliji.bos.entity.Region;
import com.wuliji.bos.utils.PageBean;

public interface RegionService {
	
	/**
	 * 批量添加
	 * @param regionList
	 */
	public void saveBatch(List<Region> regionList);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
}

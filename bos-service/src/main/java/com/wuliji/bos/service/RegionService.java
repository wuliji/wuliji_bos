package com.wuliji.bos.service;

import java.util.List;

import com.wuliji.bos.entity.Region;
import com.wuliji.bos.utils.PageBean;

public interface RegionService {
	
	/**
	 * �������
	 * @param regionList
	 */
	public void saveBatch(List<Region> regionList);

	/**
	 * ��ҳ��ѯ
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
}

package com.wuliji.bos.service;

import java.util.List;

import com.wuliji.bos.entity.Region;

public interface RegionService {
	
	/**
	 * ÅúÁ¿Ìí¼Ó
	 * @param regionList
	 */
	public void saveBatch(List<Region> regionList);
	
}

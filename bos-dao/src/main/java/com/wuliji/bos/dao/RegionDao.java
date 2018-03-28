package com.wuliji.bos.dao;

import java.util.List;

import com.wuliji.bos.dao.base.IBaseDao;
import com.wuliji.bos.entity.Region;

public interface RegionDao extends IBaseDao<Region> {
	
	/**
	 * 查询区域模糊查询
	 * @return
	 */
	List<Region> findListByQ(String q);
	
}

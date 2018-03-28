package com.wuliji.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuliji.bos.dao.RegionDao;
import com.wuliji.bos.entity.Region;
import com.wuliji.bos.service.RegionService;
import com.wuliji.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{
	
	@Autowired
	private RegionDao dao;
	
	@Override
	public void saveBatch(List<Region> regionList) {
		for (Region region : regionList) {
			dao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		List<Region> list = dao.findAll();
		return list;
	}

	@Override
	public List<Region> findListByQ(String q) {
		
		return dao.findListByQ(q);
	}
	
}

package com.wuliji.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuliji.bos.dao.DecidedzoneDao;
import com.wuliji.bos.dao.SubareaDao;
import com.wuliji.bos.entity.Decidedzone;
import com.wuliji.bos.entity.Subarea;
import com.wuliji.bos.service.DecidedzoneService;
import com.wuliji.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{

	@Autowired
	private DecidedzoneDao dao;
	@Autowired
	private SubareaDao sDao;
	
	@Override
	public void save(Decidedzone model, String[] subareaid) {
		dao.save(model);
		for (String id : subareaid) {
			Subarea subarea = sDao.findById(id);
			subarea.setDecidedzone(model);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}
	
}

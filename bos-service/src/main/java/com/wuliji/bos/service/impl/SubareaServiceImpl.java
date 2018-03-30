package com.wuliji.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuliji.bos.dao.SubareaDao;
import com.wuliji.bos.entity.Subarea;
import com.wuliji.bos.service.SubareaService;
import com.wuliji.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService{
	
	@Autowired
	private SubareaDao dao;
	
	@Override
	public void save(Subarea model) {
		dao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Subarea> findListNotRelate() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		//添加过滤条件，分区对象中decidedzone属性为null
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return dao.findByCriteria(detachedCriteria);
	}

}

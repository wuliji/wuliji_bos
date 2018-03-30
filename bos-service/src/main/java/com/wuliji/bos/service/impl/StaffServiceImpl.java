package com.wuliji.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuliji.bos.dao.StaffDao;
import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.service.StaffService;
import com.wuliji.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao staffDao;
	
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		if(StringUtils.isNoneBlank(ids)) {
			String[] staffIds = ids.split(",");
			for (String id : staffIds) {
				staffDao.executeUpdate("staff.delete", id);
			}
		}
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public List<Staff> findListIsNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		//添加过滤条件
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findByCriteria(detachedCriteria);
	}
	
}

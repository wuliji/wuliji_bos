package com.wuliji.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wuliji.bos.dao.RegionDao;
import com.wuliji.bos.dao.base.impl.IBaseDaoImpl;
import com.wuliji.bos.entity.Region;

@Repository
public class RegionDaoImpl extends IBaseDaoImpl<Region> implements RegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String hql = "from Region r where r.shortcode like ? or r.citycode like ? or "
				+ "r.province like ? or r.city like ? or r.district like ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}

}

package com.wuliji.bos.web.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.Region;
import com.wuliji.bos.entity.Subarea;
import com.wuliji.bos.service.SubareaService;
import com.wuliji.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{
	
	@Autowired
	private SubareaService service;
	
	/**
	 * 添加分区
	 */
	public String add() {
		service.save(model);
		return LIST;
	}
	
	/**
	 * 数据页面显示
	 */
	public String pageQuery() {
		DetachedCriteria dc = pageBean.getDetachedCriteria();	
		//动态添加过滤条件
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)) {
			//添加过滤条件，根据地址关键字模糊查询
			dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
		}
		Region region = model.getRegion();
		if(region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			//分区对象中关联中的区域对象的属性名称，还有别名
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)) {
				//添加过滤条件，根据地址关键字模糊查询
				dc.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if(StringUtils.isNotBlank(city)) {
				//添加过滤条件，根据地址关键字模糊查询
				dc.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if(StringUtils.isNotBlank(district)) {
				//添加过滤条件，根据地址关键字模糊查询
				dc.add(Restrictions.like("r.district", "%" + district + "%"));
			}
			
		}
		service.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage", "detachedCriteria", "pageSize","decidedzone","subareas"});
		return NONE;
	}
}

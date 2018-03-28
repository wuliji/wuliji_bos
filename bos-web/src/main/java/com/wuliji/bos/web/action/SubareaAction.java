package com.wuliji.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
		service.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage", "detachedCriteria", "pageSize","subareas"});
		return NONE;
	}
}

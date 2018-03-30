package com.wuliji.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.Decidedzone;
import com.wuliji.bos.service.DecidedzoneService;
import com.wuliji.bos.web.action.base.BaseAction;

/**
 * 定区管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{
	
	@Autowired
	private DecidedzoneService service;
	
	//属性驱动，接收多个分区id
	private String[] subareaid;

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	/**
	 * 添加定区
	 * @return
	 */
	public String add() {
		service.save(model, subareaid);
		return LIST;
	}
	
	public String pageQuery() {
		service.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage", "detachedCriteria", "pageSize","subareas","decidedzones"});
		return NONE;
	}
}

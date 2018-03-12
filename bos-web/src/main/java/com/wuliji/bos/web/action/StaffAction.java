package com.wuliji.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.service.StaffService;
import com.wuliji.bos.web.action.base.BaseAction;

/**
 * 取派员管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	
	@Autowired
	private StaffService staffService;
	
	/**
	 * 添加取派员
	 * 
	 */
	public String add() {
		staffService.save(model);
		return LIST;
	}
}

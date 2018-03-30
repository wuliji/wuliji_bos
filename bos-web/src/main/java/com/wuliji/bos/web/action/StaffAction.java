package com.wuliji.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.service.StaffService;
import com.wuliji.bos.utils.PageBean;
import com.wuliji.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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

	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {
		staffService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage", "detachedCriteria", "pageSize","decidedzones"});
		return NONE;
	}
	
	//属性驱动,删除ids的方法
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * 取派员批量删除
	 * @return
	 */
	public String deleteBatch() {
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	/**
	 * 修改取派员信息
	 * @return
	 */
	public String edit() {
		//查询数据库，取得原始数据
		Staff staff = staffService.findById(model.getId());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setName(model.getName());
		staff.setStandard(model.getStandard());
		staff.setDecidedzones(model.getDecidedzones());
		staff.setStation(model.getStation());
		//更新修改
		staffService.update(staff);
		return LIST;
	}
	
	/**
	 * 查询所有未删除的取派员
	 * @return
	 */
	public String listajax() {
		List<Staff> list = staffService.findListIsNotDelete();
		this.javaToJson(list, new String[] {"station","deltag","haspda","telephone","decidedzones"});
		return NONE;
	}
}

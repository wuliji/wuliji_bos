package com.wuliji.bos.web.action;

import java.io.IOException;

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
	
	//页面参数
	private int page;
	private int rows;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		//创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		staffService.pageQuery(pageBean);
		
		//将PageBean对象转为JSON，通过输出流写回页面
		//JSONObject将单一对象转为json
		//JSONArray将数组或集合转为json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] {"currentPage", "detachedCriteria", "pageSize"});
		
		String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
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
}

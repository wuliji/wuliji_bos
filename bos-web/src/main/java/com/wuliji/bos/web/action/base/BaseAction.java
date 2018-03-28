/**
 * 
 */
package com.wuliji.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wuliji.bos.entity.Staff;
import com.wuliji.bos.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 * @author Administrator
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	protected static final String HOME = "home";
	protected static final String LIST = "list";
	
	/**
	 * 分页模型驱动
	 */
	protected PageBean pageBean = new PageBean();
	//创建离线查询对象
	DetachedCriteria detachedCriteria = null;
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	/**
	 * 将Java转换为Json输出
	 * @param o
	 * @param excludes
	 * @throws IOException
	 */
	public void javaToJson(Object o, String[] excludes) {
		//将PageBean对象转为JSON，通过输出流写回页面
		//JSONObject将单一对象转为json
		//JSONArray将数组或集合转为json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		
		String json = JSONObject.fromObject(o, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将Java转换为Json输出
	 * @param o
	 * @param excludes
	 * @throws IOException
	 */
	public void javaToJson(List o, String[] excludes) {
		//将PageBean对象转为JSON，通过输出流写回页面
		//JSONObject将单一对象转为json
		//JSONArray将数组或集合转为json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		
		String json = JSONArray.fromObject(o, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//模型对象
	protected T model;
	
	@Override
	public T getModel() {
		return model;
	}
	
	/**
	 * 在运行时获取泛型实体对象通过反射获取model对象
	 */
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		//封装分页查询离线条件
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		//通过反射创建对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}

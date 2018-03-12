/**
 * 
 */
package com.wuliji.bos.web.action.base;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author Administrator
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	protected static final String HOME = "home";
	protected static final String LIST = "list";
	
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

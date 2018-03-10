package com.wuliji.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wuliji.bos.entity.User;
import com.wuliji.bos.utils.BOSUtils;

/**
 * 自定义拦截器，实现用户未登录自动跳转到登录页面
 * @author Administrator
 *
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

	@Override
	/**
	 * 拦截方法
	 */
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		String actionName = proxy.getActionName();
		String namespace = proxy.getNamespace();
		String url = namespace + actionName;
		
		//从session中获取用户对象
		User user = BOSUtils.getLoginUser();
		if(user == null) {
			//没有登录，跳转到登录页面
			return "login";
		}
		//放行
		return invocation.invoke();
	}

}

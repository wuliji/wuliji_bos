package com.wuliji.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.User;
import com.wuliji.bos.service.UserService;
import com.wuliji.bos.utils.BOSUtils;
import com.wuliji.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	//属性驱动，接收页面验证码
	private String checkcode;
	
	@Autowired
	private UserService userService;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * 用户登录
	 */
	public String login() {
		//校验验证码
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(code)) {
			User user = userService.login(model);
			if(user != null) {
				//登录成功,将user对象放入session，跳转到首页
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return HOME;
			}else {
				//登录失败
				this.addActionError("用户名或者密码输入错误");
				return LOGIN;
			}
		}else {
			this.addActionError("输入的验证码错误");
			return LOGIN;
		}
	}
	
	/**
	 * 用户注销
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return LOGIN;
	}
	
	/**
	 * 修改当前用户密码
	 * @throws IOException 
	 */
	public String editPassword() throws IOException {
		//标志位
		String f = "1";
		//获取当前登录用户
		User loginUser = BOSUtils.getLoginUser();
		try {
			userService.editPassWord(loginUser.getId(), model.getPassword());
		}catch(Exception e){
			f = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);
		return NONE;
	}
}

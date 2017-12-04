package com.test.action;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.domain.User;
import com.test.service.IUserService;

@Controller
@Scope(value="prototype")
public class UserAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private User user;
	private String repass;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRepass() {
		return repass;
	}

	public void setRepass(String repass) {
		this.repass = repass;
	}

	//转至登录页面
	public String tologin() throws Exception{
		return "tologin";
	}
	//用户登录
	public String login() throws Exception {
		ActionContext context = ActionContext.getContext();
		User nUser = new User();
		nUser.setName(getUser().getName());
		nUser.setPass(getUser().getPass());
		context.getSession().put("name", getUser().getName());
		setUser(nUser);
		boolean islogin = userService.islogin(nUser);
		if(islogin) {
			context.getSession().put("user", nUser.getName());
			return "loginSuccess";
		}else
			return LOGIN;
	}
	//用户注册页面
	public String toregister() throws Exception {
		return "toregister";
	}
	//用户注册
	public String register() throws Exception {
		System.out.println("用户注册");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		User nUser = new User();
		String name = getUser().getName();
		String pass = getUser().getPass();
		if(name!=null&&name.trim()!="")
			nUser.setName(name);
		if(pass!=null&&pass.trim()!="") {
			if(pass.equals(getRepass())) {
				nUser.setPass(pass);
			}else {
				response.getWriter().println("两次密码不一致！");
				response.getWriter().flush();
				response.getWriter().close();
				return "toregister";
			}
		}
		nUser.setCreateDate(new Date());
		userService.saveUser(nUser);
		return "register";
	}
	//Ajax注册验证
	public String checkregister() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//判断
		User nUser = new User();
		String name = getUser().getName();
		nUser.setName(name);
		String pass = getUser().getPass();
		String rePass = getRepass();
		if(pass!=rePass) {
			response.getWriter().println("两次密码不一致！");
		}
		//用户名判断
		if(userService.isExist(nUser)) {
			response.getWriter().println("用户名存在！");
		}
		response.getWriter().flush();
		response.getWriter().close();
		return "toregister";
	}
}

package com.test.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.UserBean;
import com.test.service.UserService;


//用户登录管理
public class UserLoginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserBean user;
	private UserBean userbean;
	private String mesg;
	UserService userService = new UserService();
	//获取ActionContext对象
	ActionContext context = ActionContext.getContext();
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	public UserBean getUserbean() {
		return userbean;
	}
	public void setUserbean(UserBean userbean) {
		this.userbean = userbean;
	}
	public String getMesg() {
		return mesg;
	}
	public void setMesg(String mesg) {
		this.mesg = mesg;
	}
	//用户登录
	public String login() throws Exception {
		// TODO Auto-generated method stub
		
		UserBean user = new UserBean();
		String loginname = getUser().getLoginname();
		String password = getUser().getPassword();
		user.setLoginname(loginname);
		user.setPassword(password);
		//判断是否登录成功
		if(userService.isLogin(user)) {
			//获取用户状态
			int status = userService.getStatus(user);
			//保存登录名到session对象
			context.getSession().put("loginname", user.getLoginname());
			//保存用户状态
			context.getSession().put("status", status);
			return SUCCESS;
		}else
			return LOGIN;
	}
	//用户注册
	public String toregister() throws Exception {
		UserBean nUser = new UserBean();
		nUser.setStatus(1);
		setUserbean(nUser);
		return SUCCESS;
	}
	public String register() throws Exception{
		boolean isExist = userService.isExist(getUserbean().getLoginname());
		int dStatus = getUserbean().getStatus();
		if(!isExist&&dStatus!=0) {
			userService.addUser(getUserbean());
			return SUCCESS;
		}
		return "fail";
	}
	//用户登录名验证
	public String checkLoginname() throws Exception{
		String loginname = getUserbean().getLoginname();
		System.out.println(loginname);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		boolean isExist = userService.isExist(loginname);
		System.out.println(isExist);
		
		if(isExist) {
			response.getWriter().println("登录名已存在！");
		}else {
			response.getWriter().println("登录名可以使用");
		}
		response.getWriter().flush();
		response.getWriter().close();
		
		return SUCCESS;
	}
	//用户注销
	public String logout() throws Exception{
		System.out.println("logout");
		context.getSession().put("loginname", null);
		return SUCCESS;
	}
}

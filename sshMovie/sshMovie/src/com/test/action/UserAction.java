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

	//ת����¼ҳ��
	public String tologin() throws Exception{
		return "tologin";
	}
	//�û���¼
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
	//�û�ע��ҳ��
	public String toregister() throws Exception {
		return "toregister";
	}
	//�û�ע��
	public String register() throws Exception {
		System.out.println("�û�ע��");
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
				response.getWriter().println("�������벻һ�£�");
				response.getWriter().flush();
				response.getWriter().close();
				return "toregister";
			}
		}
		nUser.setCreateDate(new Date());
		userService.saveUser(nUser);
		return "register";
	}
	//Ajaxע����֤
	public String checkregister() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//�ж�
		User nUser = new User();
		String name = getUser().getName();
		nUser.setName(name);
		String pass = getUser().getPass();
		String rePass = getRepass();
		if(pass!=rePass) {
			response.getWriter().println("�������벻һ�£�");
		}
		//�û����ж�
		if(userService.isExist(nUser)) {
			response.getWriter().println("�û������ڣ�");
		}
		response.getWriter().flush();
		response.getWriter().close();
		return "toregister";
	}
}

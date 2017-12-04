package com.test.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//定义过滤器  实现页面保护
@SuppressWarnings("serial")
public class MyInterceptor extends AbstractInterceptor{
	private String param;
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("拦截器invoke："+getParam());
		//获取ActionContext对象
		ActionContext context = ActionContext.getContext();
		//获取session
		Object obj = context.getSession().get("loginname");
		String result="";
		if(obj!=null) {
			result = invocation.invoke();
		}else
			result = Action.LOGIN;
		return result;
	}
	
}

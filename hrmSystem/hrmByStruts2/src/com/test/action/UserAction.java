package com.test.action;


import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.Pager;
import com.test.bean.UserBean;
import com.test.service.UserService;

//用户管理action类
public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int index;
	private List<Integer> indexs;
	private List<UserBean> userlist;
	private UserBean userbean;
	private String searchUsername;
	private Integer searchStatus;
	private Integer pageNum;
	private Integer pageSize;
	Pager<UserBean> result;
	UserService userService = new UserService();
	
	public List<UserBean> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserBean> userlist) {
		this.userlist = userlist;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Integer> getIndexs() {
		return indexs;
	}

	public void setIndexs(List<Integer> indexs) {
		this.indexs = indexs;
	}

	public UserBean getUserbean() {
		return userbean;
	}

	public void setUserbean(UserBean userbean) {
		this.userbean = userbean;
	}

	public String getSearchUsername() {
		return searchUsername;
	}

	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}

	public Integer getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(Integer searchStatus) {
		this.searchStatus = searchStatus;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Pager<UserBean> getResult() {
		return result;
	}

	public void setResult(Pager<UserBean> result) {
		this.result = result;
	}

	//获取用户列表
	public String userList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("User userlist()");
		//通过数据库获取用户列表
		getAllUserlist();
		List<UserBean> alluserlist = getUserlist();
		context.getSession().put("userlist", alluserlist);
		
		return SUCCESS;
	}
	//删除用户   只对超级用户有效
	public String deleteUser() throws Exception {
		ActionContext context = ActionContext.getContext();
		int status = (Integer)context.getSession().get("status");
		getAllUserlist();
		System.out.println("删除的Id："+getIndexs());
		if(status==0&&getIndexs()!=null) {
			userService.deleteUser(getIndexs());
		}
		return SUCCESS;
	}
	//添加用户
	public String toaddUser() throws Exception {
		return SUCCESS;
	}
	public String addUser() throws Exception {
		System.out.println("ADD!");
		userService.addUser(getUserbean());
		getAllUserlist();
		return SUCCESS;
	}
	//操作编辑用户   超级用户有效
	public String toeditUser() throws Exception{
		ActionContext context = ActionContext.getContext();
		int status = (Integer)context.getSession().get("status");
		getAllUserlist();
		if(status==0) {
			System.out.println("编辑的index："+getIndex());
			System.out.println("getPageNum:"+getPageNum());
			UserBean pUser = getUserlist().get(getIndex());
			setUserbean(pUser);
			return SUCCESS;
		}
		return INPUT;
	}
	public String editUser() throws Exception{
		getAllUserlist();
		System.out.println("edit User!");
		System.out.println("修改的userbean ID："+getUserbean().getId());
		//修改用户信息
		userService.editUser(getUserbean(), getUserbean().getId());
		getAllUserlist();
		return SUCCESS;
	}
	//查找用户   可以模糊查找  直接返回userlist
/*	public String searchUser() throws Exception{
		System.out.println("SearchUser!");
		
		List<UserBean> nUserlist = userService.queryUser(getUserbean());
		setUserlist(nUserlist);
		System.out.println(getUserlist());
		List<UserBean> nUserlist = userService.queryUser(getUserbean());
		setUserlist(nUserlist);
		return SUCCESS;
	}*/
	//获取用户列表
	public void getAllUserlist() {
		//System.out.println("用户状态:"+status);
/*		UserBean sUser = new UserBean();
		sUser.setUsername(searchUsername);
		sUser.setStatus(searchStatus);
		List<UserBean> nUserlist = userService.queryUser(sUser);
		setUserlist(nUserlist);*/
		ActionContext context = ActionContext.getContext();
		UserBean sUser = new UserBean();
		sUser.setUsername(searchUsername);
		sUser.setStatus(searchStatus);
		if(getPageNum()==null)
			setPageNum(1);
		if(getPageSize()==null)
			setPageSize(5);
		Pager<UserBean> nResult = userService.findUser(sUser, getPageNum(), pageSize);
		setResult(nResult);
		context.getSession().put("result", result);
		setUserlist(result.getDataList());
		//setUserlist(userService.getUserlist());
	}
}

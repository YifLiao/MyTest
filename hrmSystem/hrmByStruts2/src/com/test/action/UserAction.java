package com.test.action;


import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.Pager;
import com.test.bean.UserBean;
import com.test.service.UserService;

//�û�����action��
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

	//��ȡ�û��б�
	public String userList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("User userlist()");
		//ͨ�����ݿ��ȡ�û��б�
		getAllUserlist();
		List<UserBean> alluserlist = getUserlist();
		context.getSession().put("userlist", alluserlist);
		
		return SUCCESS;
	}
	//ɾ���û�   ֻ�Գ����û���Ч
	public String deleteUser() throws Exception {
		ActionContext context = ActionContext.getContext();
		int status = (Integer)context.getSession().get("status");
		getAllUserlist();
		System.out.println("ɾ����Id��"+getIndexs());
		if(status==0&&getIndexs()!=null) {
			userService.deleteUser(getIndexs());
		}
		return SUCCESS;
	}
	//����û�
	public String toaddUser() throws Exception {
		return SUCCESS;
	}
	public String addUser() throws Exception {
		System.out.println("ADD!");
		userService.addUser(getUserbean());
		getAllUserlist();
		return SUCCESS;
	}
	//�����༭�û�   �����û���Ч
	public String toeditUser() throws Exception{
		ActionContext context = ActionContext.getContext();
		int status = (Integer)context.getSession().get("status");
		getAllUserlist();
		if(status==0) {
			System.out.println("�༭��index��"+getIndex());
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
		System.out.println("�޸ĵ�userbean ID��"+getUserbean().getId());
		//�޸��û���Ϣ
		userService.editUser(getUserbean(), getUserbean().getId());
		getAllUserlist();
		return SUCCESS;
	}
	//�����û�   ����ģ������  ֱ�ӷ���userlist
/*	public String searchUser() throws Exception{
		System.out.println("SearchUser!");
		
		List<UserBean> nUserlist = userService.queryUser(getUserbean());
		setUserlist(nUserlist);
		System.out.println(getUserlist());
		List<UserBean> nUserlist = userService.queryUser(getUserbean());
		setUserlist(nUserlist);
		return SUCCESS;
	}*/
	//��ȡ�û��б�
	public void getAllUserlist() {
		//System.out.println("�û�״̬:"+status);
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

package com.test.service;

import java.util.List;

import com.test.bean.Pager;
import com.test.bean.UserBean;
import com.test.dao.IUserDao;
import com.test.dao.factory.DaoFactory;

public class UserService {
	//��ȡUserDao����
	private IUserDao userDao;
	public UserService() {
		userDao = DaoFactory.getUserDao();
	}
	//�û���¼��֤
	public boolean isLogin(UserBean user){
		boolean isLogin = false;
		List<UserBean> userlist = userDao.queryUserByLogin(user);
		if(userlist!=null&&userlist.size()>0) 
			isLogin = true;
		return isLogin;
	}
	//�û���¼�ɹ���ȡ�û�״̬
	public int getStatus(UserBean user) {
		int status  =1;
		List<UserBean> userlist = userDao.queryUserByLogin(user);
		for(UserBean users:userlist) {
			status = users.getStatus();
		}
		return status;
	}
	//��ȡ�û��б�
	public List<UserBean> getUserlist(){
		return userDao.queryUserlist();
	}
	//ɾ���û�
	public int deleteUser(List<Integer> ids) {
		int pos=0;
		for(int id:ids) {
			pos = userDao.deleteUser(id);
		}
		return pos;
	}
	//����û�
	public int addUser(UserBean user) {
		return userDao.insertUser(user);
	}
	//�༭�û�
	public int editUser(UserBean nUser,int id) {
		return userDao.updateUser(nUser, id);
	}
	//�����û�
	public List<UserBean> queryUser(UserBean user){
		return userDao.queryUser(user);
	}
	//�û���¼����֤
	public boolean isExist(String loginname) {
		boolean isExist = false;
		List<UserBean> userlist = userDao.checkLoginname(loginname);
		if(userlist!=null&&userlist.size()>0) {
			isExist = true;
		}
		return isExist;
	}
	//�����û�
	public Pager<UserBean> findUser(UserBean userModel,int pageNum,int pageSize){
		return userDao.findUser(userModel, pageNum, pageSize);
	}
	
	public int queryUserCount(UserBean user) {
		// TODO Auto-generated method stub
		return userDao.queryUserCount(user);
	}
}

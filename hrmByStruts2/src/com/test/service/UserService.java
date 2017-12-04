package com.test.service;

import java.util.List;

import com.test.bean.Pager;
import com.test.bean.UserBean;
import com.test.dao.IUserDao;
import com.test.dao.factory.DaoFactory;

public class UserService {
	//获取UserDao对象
	private IUserDao userDao;
	public UserService() {
		userDao = DaoFactory.getUserDao();
	}
	//用户登录验证
	public boolean isLogin(UserBean user){
		boolean isLogin = false;
		List<UserBean> userlist = userDao.queryUserByLogin(user);
		if(userlist!=null&&userlist.size()>0) 
			isLogin = true;
		return isLogin;
	}
	//用户登录成功获取用户状态
	public int getStatus(UserBean user) {
		int status  =1;
		List<UserBean> userlist = userDao.queryUserByLogin(user);
		for(UserBean users:userlist) {
			status = users.getStatus();
		}
		return status;
	}
	//获取用户列表
	public List<UserBean> getUserlist(){
		return userDao.queryUserlist();
	}
	//删除用户
	public int deleteUser(List<Integer> ids) {
		int pos=0;
		for(int id:ids) {
			pos = userDao.deleteUser(id);
		}
		return pos;
	}
	//添加用户
	public int addUser(UserBean user) {
		return userDao.insertUser(user);
	}
	//编辑用户
	public int editUser(UserBean nUser,int id) {
		return userDao.updateUser(nUser, id);
	}
	//查找用户
	public List<UserBean> queryUser(UserBean user){
		return userDao.queryUser(user);
	}
	//用户登录名验证
	public boolean isExist(String loginname) {
		boolean isExist = false;
		List<UserBean> userlist = userDao.checkLoginname(loginname);
		if(userlist!=null&&userlist.size()>0) {
			isExist = true;
		}
		return isExist;
	}
	//查找用户
	public Pager<UserBean> findUser(UserBean userModel,int pageNum,int pageSize){
		return userDao.findUser(userModel, pageNum, pageSize);
	}
	
	public int queryUserCount(UserBean user) {
		// TODO Auto-generated method stub
		return userDao.queryUserCount(user);
	}
}

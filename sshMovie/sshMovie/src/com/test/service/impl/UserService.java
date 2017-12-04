package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.IUserDao;
import com.test.domain.User;
import com.test.service.IUserService;

@Service
public class UserService implements IUserService{

	private IUserDao userDao;
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	//用户登录判断
	@Override
	public boolean islogin(User user) {
		// TODO Auto-generated method stub
		boolean islogin=false;
		List<User> userlist = userDao.userlogin(user);
		if(userlist!=null&&userlist.size()>0) {
			islogin=true;
		}
		return islogin;
	}
	//用户名是否存在
	@Override
	public boolean isExist(User user) {
		boolean isExist = false;
		List<User> userlist = userDao.userlogin(user);
		if(userlist!=null&&userlist.size()>0) {
			//存在
			isExist = true;
		}
		return isExist;
	}
	
	@Transactional(readOnly=false)
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}
}

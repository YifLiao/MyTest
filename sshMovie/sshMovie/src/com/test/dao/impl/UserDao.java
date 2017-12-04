package com.test.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.dao.IUserDao;
import com.test.domain.User;

@Repository
public class UserDao extends HibernateDaoSupport implements IUserDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory); 
	}
	//用户登录验证
	@Override
	public List<User> userlogin(User user) {
		// TODO Auto-generated method stub
		List<User> userlist = getHibernateTemplate().findByExample(user);
		return userlist;
	}
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(user);
	}

}

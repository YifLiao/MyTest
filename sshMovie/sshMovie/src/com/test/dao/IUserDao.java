package com.test.dao;

import java.util.List;

import com.test.domain.User;

public interface IUserDao {
	public List<User> userlogin(User user);
	public void saveUser(User user);
}

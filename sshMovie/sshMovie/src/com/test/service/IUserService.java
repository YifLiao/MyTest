package com.test.service;

import com.test.domain.User;

public interface IUserService {
	public boolean islogin(User user);
	public boolean isExist(User user);
	public void saveUser(User user);
}

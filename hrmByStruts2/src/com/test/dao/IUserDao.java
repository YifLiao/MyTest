package com.test.dao;

import java.util.List;

import com.test.bean.Pager;
import com.test.bean.UserBean;

//定义用户接口
public interface IUserDao {
	//用户登录
	public List<UserBean> queryUserByLogin(UserBean user);
	//获取用户列表
	public List<UserBean> queryUserlist();
	//删除用户
	public int deleteUser(int id);
	//添加用户
	public int insertUser(UserBean user);
	//编辑用户
	public int updateUser(UserBean userUpdate,int id);
	//搜索用户
	public List<UserBean> queryUser(UserBean user);
	//分页查找
	public Pager<UserBean> findUser(UserBean userModel,int pageNum,int pageSize);
	//统计User表有多少记录
	public int queryUserCount(UserBean user);
/*	//用户注册
	public int registerUser(UserBean user); */
	//登录名判断
	public List<UserBean> checkLoginname(String loginname);
}

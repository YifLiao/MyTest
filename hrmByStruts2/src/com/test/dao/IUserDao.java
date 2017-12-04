package com.test.dao;

import java.util.List;

import com.test.bean.Pager;
import com.test.bean.UserBean;

//�����û��ӿ�
public interface IUserDao {
	//�û���¼
	public List<UserBean> queryUserByLogin(UserBean user);
	//��ȡ�û��б�
	public List<UserBean> queryUserlist();
	//ɾ���û�
	public int deleteUser(int id);
	//����û�
	public int insertUser(UserBean user);
	//�༭�û�
	public int updateUser(UserBean userUpdate,int id);
	//�����û�
	public List<UserBean> queryUser(UserBean user);
	//��ҳ����
	public Pager<UserBean> findUser(UserBean userModel,int pageNum,int pageSize);
	//ͳ��User���ж��ټ�¼
	public int queryUserCount(UserBean user);
/*	//�û�ע��
	public int registerUser(UserBean user); */
	//��¼���ж�
	public List<UserBean> checkLoginname(String loginname);
}

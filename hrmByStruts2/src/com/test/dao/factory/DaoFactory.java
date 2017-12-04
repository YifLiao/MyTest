package com.test.dao.factory;

import com.test.dao.IDeptDao;
import com.test.dao.IDocumDao;
import com.test.dao.IEmplDao;
import com.test.dao.IJobDao;
import com.test.dao.IUserDao;
import com.test.dao.impl.DeptDaoImpl;
import com.test.dao.impl.DocumDaoImpl;
import com.test.dao.impl.EmplDaoImpl;
import com.test.dao.impl.JobDaoImpl;
import com.test.dao.impl.UserDaoImpl;

public class DaoFactory {
	//��ȡ�û�Dao����
	public static IUserDao getUserDao() {
		return new UserDaoImpl();
	}
	//��ȡ����Dao����
	public static IDeptDao getDeptDao() {
		return new DeptDaoImpl();
	}
	//��ȡְλDao����
	public static IJobDao getJobDao() {
		return new JobDaoImpl();
	}
	//��ȡԱ��Dao����
	public static IEmplDao getEmplDao() {
		return new EmplDaoImpl();
	}
	//��ȡ�ļ�Dao����
	public static IDocumDao getDocumDao() {
		return new DocumDaoImpl();
	}
}

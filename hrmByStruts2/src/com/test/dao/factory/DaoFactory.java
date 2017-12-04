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
	//获取用户Dao对象
	public static IUserDao getUserDao() {
		return new UserDaoImpl();
	}
	//获取部门Dao对象
	public static IDeptDao getDeptDao() {
		return new DeptDaoImpl();
	}
	//获取职位Dao对象
	public static IJobDao getJobDao() {
		return new JobDaoImpl();
	}
	//获取员工Dao对象
	public static IEmplDao getEmplDao() {
		return new EmplDaoImpl();
	}
	//获取文件Dao对象
	public static IDocumDao getDocumDao() {
		return new DocumDaoImpl();
	}
}

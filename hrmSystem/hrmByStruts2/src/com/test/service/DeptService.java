package com.test.service;

import java.util.List;

import com.test.bean.DeptBean;
import com.test.bean.Pager;
import com.test.dao.IDeptDao;
import com.test.dao.factory.DaoFactory;

public class DeptService {
	//获取Dao对象
	private IDeptDao deptDao;
	public DeptService() {
		deptDao = DaoFactory.getDeptDao();
	}
	
	//获取部门列表
	public List<DeptBean> getDeptlist(){
		return deptDao.queryDeptlist();
	}
	//删除部门
	public int deleteDept(List<Integer> ids) {
		int pos = 0 ;
		for(int id:ids) {
			pos = deptDao.deleteDept(id);
		}
		return pos;
	}
	//添加部门
	public int addDept(DeptBean dept) {
		return deptDao.insertDept(dept);
	}
	//编辑部门
	public int editDept(DeptBean dept,int id) {
		return deptDao.updateDept(dept, id);
	}
	//查找用户
	public List<DeptBean> queryDept(DeptBean dept){
		return deptDao.queryDept(dept);
	}
	//
	public Pager<DeptBean> findDept(DeptBean deptModel,int pageNum,int pageSize){
		return deptDao.findDept(deptModel, pageNum, pageSize);
	}
}

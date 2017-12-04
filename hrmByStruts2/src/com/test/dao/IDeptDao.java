package com.test.dao;

import java.util.List;

import com.test.bean.DeptBean;
import com.test.bean.Pager;

public interface IDeptDao {
	//获取部门
	public List<DeptBean> queryDeptlist();
	//删除部门
	public int deleteDept(int id);
	//添加部门
	public int insertDept(DeptBean dept);
	//编辑部门
	public int updateDept(DeptBean deptUpdate,int id);
	//搜索用户
	public List<DeptBean> queryDept(DeptBean dept);
	//分页查找
	public Pager<DeptBean> findDept(DeptBean deptModel,int pageNum,int pageSize);
	//统计计录数
	public int queryDeptCount(DeptBean dept);
}

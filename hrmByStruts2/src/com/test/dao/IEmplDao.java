package com.test.dao;

import java.util.List;

import com.test.bean.EmplBean;
import com.test.bean.Pager;

public interface IEmplDao {
	//获取员工列表
	public List<EmplBean> queryAllEmp();
	public List<EmplBean> queryEmpllist(EmplBean empl);
	//删除员工
	public int deleteEmpl(int id);
	//添加员工
	public int insertEmpl(EmplBean empl); 
	//编辑员工
	public int updateEmpl(EmplBean emplUpdate,int id);
	//判断身份证是否存在
	public int isCardIdExist(String cardId); 
	 //分页查找
	public Pager<EmplBean> findEmpl(EmplBean emplModel,int pageNum,int pageSize);
	//统计计录数
	public int queryEmplCount(EmplBean empl);
}

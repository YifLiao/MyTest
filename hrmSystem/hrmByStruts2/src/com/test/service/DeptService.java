package com.test.service;

import java.util.List;

import com.test.bean.DeptBean;
import com.test.bean.Pager;
import com.test.dao.IDeptDao;
import com.test.dao.factory.DaoFactory;

public class DeptService {
	//��ȡDao����
	private IDeptDao deptDao;
	public DeptService() {
		deptDao = DaoFactory.getDeptDao();
	}
	
	//��ȡ�����б�
	public List<DeptBean> getDeptlist(){
		return deptDao.queryDeptlist();
	}
	//ɾ������
	public int deleteDept(List<Integer> ids) {
		int pos = 0 ;
		for(int id:ids) {
			pos = deptDao.deleteDept(id);
		}
		return pos;
	}
	//��Ӳ���
	public int addDept(DeptBean dept) {
		return deptDao.insertDept(dept);
	}
	//�༭����
	public int editDept(DeptBean dept,int id) {
		return deptDao.updateDept(dept, id);
	}
	//�����û�
	public List<DeptBean> queryDept(DeptBean dept){
		return deptDao.queryDept(dept);
	}
	//
	public Pager<DeptBean> findDept(DeptBean deptModel,int pageNum,int pageSize){
		return deptDao.findDept(deptModel, pageNum, pageSize);
	}
}

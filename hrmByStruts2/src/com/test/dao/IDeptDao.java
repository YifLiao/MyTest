package com.test.dao;

import java.util.List;

import com.test.bean.DeptBean;
import com.test.bean.Pager;

public interface IDeptDao {
	//��ȡ����
	public List<DeptBean> queryDeptlist();
	//ɾ������
	public int deleteDept(int id);
	//��Ӳ���
	public int insertDept(DeptBean dept);
	//�༭����
	public int updateDept(DeptBean deptUpdate,int id);
	//�����û�
	public List<DeptBean> queryDept(DeptBean dept);
	//��ҳ����
	public Pager<DeptBean> findDept(DeptBean deptModel,int pageNum,int pageSize);
	//ͳ�Ƽ�¼��
	public int queryDeptCount(DeptBean dept);
}

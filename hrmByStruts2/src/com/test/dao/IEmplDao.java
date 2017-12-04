package com.test.dao;

import java.util.List;

import com.test.bean.EmplBean;
import com.test.bean.Pager;

public interface IEmplDao {
	//��ȡԱ���б�
	public List<EmplBean> queryAllEmp();
	public List<EmplBean> queryEmpllist(EmplBean empl);
	//ɾ��Ա��
	public int deleteEmpl(int id);
	//���Ա��
	public int insertEmpl(EmplBean empl); 
	//�༭Ա��
	public int updateEmpl(EmplBean emplUpdate,int id);
	//�ж����֤�Ƿ����
	public int isCardIdExist(String cardId); 
	 //��ҳ����
	public Pager<EmplBean> findEmpl(EmplBean emplModel,int pageNum,int pageSize);
	//ͳ�Ƽ�¼��
	public int queryEmplCount(EmplBean empl);
}

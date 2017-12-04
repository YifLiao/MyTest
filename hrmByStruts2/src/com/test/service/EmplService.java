package com.test.service;

import java.util.List;

import com.test.bean.EmplBean;
import com.test.bean.Pager;
import com.test.dao.IEmplDao;
import com.test.dao.factory.DaoFactory;

public class EmplService {
	private IEmplDao emplDao;
	public EmplService() {
		emplDao = DaoFactory.getEmplDao();
	}
	
	//��ȡԱ����
	public List<EmplBean> getAllEmpllist(){
		return emplDao.queryAllEmp();
	}
	public List<EmplBean> getEmpllist(EmplBean empl){
		return emplDao.queryEmpllist(empl);
	}
	//ɾ��Ա��
	public int deleteEmpl(int ids[]) {
		int pos = 0;
		for(int id:ids) {
			pos = emplDao.deleteEmpl(id);
		}
		return pos;
	}
	//���Ա��
	public int addEmpl(EmplBean empl) {
		
		return emplDao.insertEmpl(empl);
	}
	//�༭Ա��
	public int updateEmpl(EmplBean emplUpdate, int id) {
		return emplDao.updateEmpl(emplUpdate, id);
	}
	//�ж�Ա�����֤�Ƿ����
	public boolean isCardIdExist(String cardId) {
		boolean isExist = false;
		int id = emplDao.isCardIdExist(cardId);
		if(id>=0) {
			isExist = true;
		}
		return isExist;
	}
	public Pager<EmplBean> findEmpl(EmplBean emplModel,int pageNum,int pageSize){
		return emplDao.findEmpl(emplModel, pageNum, pageSize);
	}
}

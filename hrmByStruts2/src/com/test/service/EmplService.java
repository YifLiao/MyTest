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
	
	//获取员工表
	public List<EmplBean> getAllEmpllist(){
		return emplDao.queryAllEmp();
	}
	public List<EmplBean> getEmpllist(EmplBean empl){
		return emplDao.queryEmpllist(empl);
	}
	//删除员工
	public int deleteEmpl(int ids[]) {
		int pos = 0;
		for(int id:ids) {
			pos = emplDao.deleteEmpl(id);
		}
		return pos;
	}
	//添加员工
	public int addEmpl(EmplBean empl) {
		
		return emplDao.insertEmpl(empl);
	}
	//编辑员工
	public int updateEmpl(EmplBean emplUpdate, int id) {
		return emplDao.updateEmpl(emplUpdate, id);
	}
	//判断员工身份证是否存在
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

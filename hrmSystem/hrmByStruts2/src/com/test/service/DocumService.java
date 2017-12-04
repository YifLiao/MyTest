package com.test.service;

import java.util.List;

import com.test.bean.DocumentBean;
import com.test.bean.Pager;
import com.test.dao.IDocumDao;
import com.test.dao.factory.DaoFactory;

public class DocumService {
	//��ȡDao����
	private IDocumDao documDao;
	public DocumService() {
		documDao = DaoFactory.getDocumDao();
	}
	//�ϴ��ļ�
	public int uploadDocum(DocumentBean docum) {
		return documDao.insertDocument(docum);
	}
	//ɾ���ļ�
	public int deleteDocum(List<Integer> ids) {
		int pos = 0 ;
		for(int id:ids) {
			pos = documDao.deleteDocument(id);
		}
		return pos;
	}
	//�����ļ�
	public List<DocumentBean> queryDocum(DocumentBean docum){
		return documDao.queryDocument(docum);
	}
	//�༭�ļ�
	public int editDocum(DocumentBean documUpdate,int id) {
		return documDao.updateDocum(documUpdate, id);
	}
	
	//
	public Pager<DocumentBean> findDept(DocumentBean documModel,int pageNum,int pageSize){
		return documDao.findDocum(documModel, pageNum, pageSize);
	}
	//�����ļ�
/*	public DocumentBean getFileBeanById(DocumentBean docum) {
		return documDao.getFileBeanById(docum);
	}*/
}

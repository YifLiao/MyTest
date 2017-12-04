package com.test.service;

import java.util.List;

import com.test.bean.DocumentBean;
import com.test.bean.Pager;
import com.test.dao.IDocumDao;
import com.test.dao.factory.DaoFactory;

public class DocumService {
	//获取Dao对象
	private IDocumDao documDao;
	public DocumService() {
		documDao = DaoFactory.getDocumDao();
	}
	//上传文件
	public int uploadDocum(DocumentBean docum) {
		return documDao.insertDocument(docum);
	}
	//删除文件
	public int deleteDocum(List<Integer> ids) {
		int pos = 0 ;
		for(int id:ids) {
			pos = documDao.deleteDocument(id);
		}
		return pos;
	}
	//查找文件
	public List<DocumentBean> queryDocum(DocumentBean docum){
		return documDao.queryDocument(docum);
	}
	//编辑文件
	public int editDocum(DocumentBean documUpdate,int id) {
		return documDao.updateDocum(documUpdate, id);
	}
	
	//
	public Pager<DocumentBean> findDept(DocumentBean documModel,int pageNum,int pageSize){
		return documDao.findDocum(documModel, pageNum, pageSize);
	}
	//下载文件
/*	public DocumentBean getFileBeanById(DocumentBean docum) {
		return documDao.getFileBeanById(docum);
	}*/
}

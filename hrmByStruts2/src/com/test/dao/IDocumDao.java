package com.test.dao;

import java.util.List;

import com.test.bean.DocumentBean;
import com.test.bean.Pager;

public interface IDocumDao {
	//上传文件
	public int insertDocument(DocumentBean docum);
	//删除文件
	public int deleteDocument(int id);
	//查询文件
	public List<DocumentBean> queryDocument(DocumentBean docum);
	//下载文件
	//public DocumentBean getFileBeanById(DocumentBean docum);
	//编辑文件
	public int updateDocum(DocumentBean documUpdate,int id);
	//分页查找
	public Pager<DocumentBean> findDocum(DocumentBean documModel,int pageNum,int pageSize);
	//统计计录数
	public int queryDocumCount(DocumentBean docum);
}

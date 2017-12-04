package com.test.dao;

import java.util.List;

import com.test.bean.DocumentBean;
import com.test.bean.Pager;

public interface IDocumDao {
	//�ϴ��ļ�
	public int insertDocument(DocumentBean docum);
	//ɾ���ļ�
	public int deleteDocument(int id);
	//��ѯ�ļ�
	public List<DocumentBean> queryDocument(DocumentBean docum);
	//�����ļ�
	//public DocumentBean getFileBeanById(DocumentBean docum);
	//�༭�ļ�
	public int updateDocum(DocumentBean documUpdate,int id);
	//��ҳ����
	public Pager<DocumentBean> findDocum(DocumentBean documModel,int pageNum,int pageSize);
	//ͳ�Ƽ�¼��
	public int queryDocumCount(DocumentBean docum);
}

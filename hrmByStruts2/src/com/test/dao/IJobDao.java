package com.test.dao;

import java.util.List;

import com.test.bean.JobBean;
import com.test.bean.Pager;

public interface IJobDao {
	//��ȡְλ
	public List<JobBean> queryJoblist();
	//ɾ��ְλ
	public int deleteJob(int id);
	//���ְλ
	public int insertJob(JobBean job);
	//�༭ְλ
	public int updateJob(JobBean jobUpdate,int id);
	//����ְλ
	public List<JobBean> queryJob(JobBean job);
	//
	public Pager<JobBean> findJob(JobBean jobModel,int pageNum,int pageSize);
	//
	public int queryJobCount(JobBean job);
}

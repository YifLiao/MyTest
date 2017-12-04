package com.test.service;

import java.util.List;

import com.test.bean.JobBean;
import com.test.bean.Pager;
import com.test.dao.IJobDao;
import com.test.dao.factory.DaoFactory;

public class JobService {
	//��ȡDao����
	private IJobDao jobDao;
	public JobService() {
		jobDao = DaoFactory.getJobDao();
	}
	
	//��ȡְλ�б�
	public List<JobBean> getJoblist(){
		return jobDao.queryJoblist();
	}
	//ɾ��ְλ
	public int deleteJob(List<Integer> ids) {
		int pos = 0 ;
		for(int id:ids) {
			pos = jobDao.deleteJob(id);
		}
		return pos;
	}
	//���ְλ
	public int addJob(JobBean job) {
		return jobDao.insertJob(job);
	}
	//�༭ְλ
	public int editJob(JobBean job,int id) {
		return jobDao.updateJob(job, id);
	}
	//����ְλ
	public List<JobBean> queryJob(JobBean job){
		return jobDao.queryJob(job);
	}
	//
	public Pager<JobBean> findJob(JobBean jobModel,int pageNum,int pageSize){
		return jobDao.findJob(jobModel, pageNum, pageSize);
	}
}

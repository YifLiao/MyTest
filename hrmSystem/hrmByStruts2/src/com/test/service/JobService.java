package com.test.service;

import java.util.List;

import com.test.bean.JobBean;
import com.test.bean.Pager;
import com.test.dao.IJobDao;
import com.test.dao.factory.DaoFactory;

public class JobService {
	//获取Dao对象
	private IJobDao jobDao;
	public JobService() {
		jobDao = DaoFactory.getJobDao();
	}
	
	//获取职位列表
	public List<JobBean> getJoblist(){
		return jobDao.queryJoblist();
	}
	//删除职位
	public int deleteJob(List<Integer> ids) {
		int pos = 0 ;
		for(int id:ids) {
			pos = jobDao.deleteJob(id);
		}
		return pos;
	}
	//添加职位
	public int addJob(JobBean job) {
		return jobDao.insertJob(job);
	}
	//编辑职位
	public int editJob(JobBean job,int id) {
		return jobDao.updateJob(job, id);
	}
	//查找职位
	public List<JobBean> queryJob(JobBean job){
		return jobDao.queryJob(job);
	}
	//
	public Pager<JobBean> findJob(JobBean jobModel,int pageNum,int pageSize){
		return jobDao.findJob(jobModel, pageNum, pageSize);
	}
}

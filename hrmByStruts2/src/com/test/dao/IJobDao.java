package com.test.dao;

import java.util.List;

import com.test.bean.JobBean;
import com.test.bean.Pager;

public interface IJobDao {
	//获取职位
	public List<JobBean> queryJoblist();
	//删除职位
	public int deleteJob(int id);
	//添加职位
	public int insertJob(JobBean job);
	//编辑职位
	public int updateJob(JobBean jobUpdate,int id);
	//搜索职位
	public List<JobBean> queryJob(JobBean job);
	//
	public Pager<JobBean> findJob(JobBean jobModel,int pageNum,int pageSize);
	//
	public int queryJobCount(JobBean job);
}

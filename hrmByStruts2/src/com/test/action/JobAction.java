package com.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.Constant;
import com.test.bean.JobBean;
import com.test.bean.Pager;
import com.test.service.JobService;

public class JobAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private int index;
	private List<Integer> indexs;
	private List<JobBean> joblist;
	private JobBean jobbean;
	private String searchName;
	private Integer pageNum;
	private Pager<JobBean> result;
	JobService jobService = new JobService();
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Integer> getIndexs() {
		return indexs;
	}
	public void setIndexs(List<Integer> indexs) {
		this.indexs = indexs;
	}
	public List<JobBean> getJoblist() {
		return joblist;
	}
	public void setJoblist(List<JobBean> joblist) {
		this.joblist = joblist;
	}
	public JobBean getJobbean() {
		return jobbean;
	}
	public void setJobbean(JobBean jobbean) {
		this.jobbean = jobbean;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Pager<JobBean> getResult() {
		return result;
	}
	public void setResult(Pager<JobBean> result) {
		this.result = result;
	}
	//��ȡְλ�б�
	public String jobList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("Job joblist()");
		//��ȡ�б�
		getAllJoblist();
		List<JobBean> alljoblist = getJoblist();
		context.getSession().put("joblist", alljoblist);
		return SUCCESS;
	}
	//ɾ��ְλ   ֻ�Գ����û���Ч
	public String deleteJob() throws Exception {
		ActionContext context = ActionContext.getContext();
		int status = (Integer)context.getSession().get("status");
		getAllJoblist();
		System.out.println("ɾ����Id��"+getIndexs());
		if(status==0&&getIndexs()!=null) {
			jobService.deleteJob(getIndexs());
		}
		return SUCCESS;
	}
	//���ְλ
	public String toaddJob() throws Exception{
		return SUCCESS;
	}
	public String addJob() throws Exception{
		System.out.println("ADD Job!");
		jobService.addJob(getJobbean());
		getAllJoblist();
		return SUCCESS;
	}
	//�����༭�û�   �����û���Ч
	public String toeditJob() throws Exception{
		ActionContext context = ActionContext.getContext();
		int status = (Integer)context.getSession().get("status");
		getAllJoblist();
		if(status==0) {
			System.out.println("job�༭��index��"+getIndex());
			JobBean pJob = getJoblist().get(getIndex());
			setJobbean(pJob);
			return SUCCESS;
		}
		return INPUT;
	}
	public String editJob() throws Exception{
		getAllJoblist();
		System.out.println("edit Job!");
		System.out.println("�޸ĵ�jobbean ID��"+getJobbean().getId());
		//�޸�ְλ��Ϣ
		jobService.editJob(getJobbean(), getJobbean().getId());
		getAllJoblist();
		return SUCCESS;
	}
	//��ȡְλ�б�
	public void getAllJoblist() {
		/*JobBean sJob = new JobBean();
		sJob.setName(searchName);
		List<JobBean> nJoblist = jobService.queryJob(sJob);
		setJoblist(nJoblist);*/
		ActionContext context = ActionContext.getContext();
		JobBean sJob = new JobBean();
		sJob.setName(searchName);
		if(getPageNum()==null)
			setPageNum(1);
		Pager<JobBean> nResult = jobService.findJob(sJob, getPageNum(), Constant.DEFAULT_PAGE_SIZE);
		setResult(nResult);
		context.getSession().put("jobResult", result);
		setJoblist(result.getDataList());
		
		//setJoblist(jobService.getJoblist());
	}
}

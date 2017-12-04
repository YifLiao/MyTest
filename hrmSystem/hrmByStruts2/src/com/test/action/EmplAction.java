package com.test.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.Constant;
import com.test.bean.DeptBean;
import com.test.bean.EmplBean;
import com.test.bean.JobBean;
import com.test.bean.Pager;
import com.test.service.DeptService;
import com.test.service.EmplService;
import com.test.service.JobService;

public class EmplAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int index;
	private int[] indexs;
	private List<JobBean> joblist = new ArrayList<>();     //��ȡְλ�б�
	private List<DeptBean> deptlist = new ArrayList<>();   //��ȡ�����б�
	private List<EmplBean> empllist = new ArrayList<>();
	private int searchJobname = -1;
	private int searchDeptname = -1;
	private int searchSex = -1;
	private String searchName;
	private String searchCardId;
	private String searchTel;
	private EmplBean emplbean;
	private Integer pageNum;
	private Pager<EmplBean> result;
	EmplService emplService = new EmplService();
	JobService jobService = new JobService();
	DeptService deptService = new DeptService();
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int[] getIndexs() {
		return indexs;
	}
	public void setIndexs(int[] indexs) {
		this.indexs = indexs;
	}
	public List<JobBean> getJoblist() {
		return joblist;
	}
	public void setJoblist(List<JobBean> joblist) {
		this.joblist = joblist;
	}
	
	public List<DeptBean> getDeptlist() {
		return deptlist;
	}
	public void setDeptlist(List<DeptBean> deptlist) {
		this.deptlist = deptlist;
	}
	
	public List<EmplBean> getEmpllist() {
		return empllist;
	}
	public void setEmpllist(List<EmplBean> empllist) {
		this.empllist = empllist;
	}
	public int getSearchJobname() {
		return searchJobname;
	}
	public void setSearchJobname(int searchJobname) {
		this.searchJobname = searchJobname;
	}
	public int getSearchDeptname() {
		return searchDeptname;
	}
	public void setSearchDeptname(int searchDeptname) {
		this.searchDeptname = searchDeptname;
	}
	public int getSearchSex() {
		return searchSex;
	}
	public void setSearchSex(int searchSex) {
		this.searchSex = searchSex;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchCardId() {
		return searchCardId;
	}
	public void setSearchCardId(String searchCardId) {
		this.searchCardId = searchCardId;
	}
	public String getSearchTel() {
		return searchTel;
	}
	public void setSearchTel(String searchTel) {
		this.searchTel = searchTel;
	}
	public EmplBean getEmplbean() {
		return emplbean;
	}
	public void setEmplbean(EmplBean emplbean) {
		this.emplbean = emplbean;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Pager<EmplBean> getResult() {
		return result;
	}
	public void setResult(Pager<EmplBean> result) {
		this.result = result;
	}
	//��ȡԱ���б�
	public String empList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("empList()");
		getAllEmpllist();
		List<EmplBean> allEmpllist = new ArrayList<>();
		allEmpllist = getEmpllist();
		context.getSession().put("empllist", allEmpllist);
		System.out.println("allEmpllist is:"+allEmpllist);
		return SUCCESS;
	}
	//ɾ��Ա��
	public String deleteEmpl() throws Exception {
		ActionContext context = ActionContext.getContext();
		//��ȡ�û�״̬
		int status = (Integer)context.getSession().get("status");
		System.out.println("ɾ��Ա����");
		System.out.println("ɾ��Ա����ID��"+getIndexs());
		if(status==0&&getIndexs()!=null) {
			//����Ա��Ȩ��ɾ��Ա��
			emplService.deleteEmpl(getIndexs());
		}
		return SUCCESS;
	}
	//���Ա��ҳ��
	public String toaddEmpl() throws Exception {
		getJobAndDept();
		return SUCCESS;
	}
	//���Ա��
	public String addEmpl() throws Exception{
		System.out.println("ADD Empl!");
		emplService.addEmpl(getEmplbean());
		getAllEmpllist();
		return SUCCESS;
	}
	//�༭Ա��ҳ��
	public String toeditEmpl() throws Exception{
		ActionContext context = ActionContext.getContext();
		//��ȡ�û�״̬
		int status = (Integer)context.getSession().get("status");
		getAllEmpllist();
		if(status==0) {
			System.out.println("empl�༭��index��"+getIndex());
			EmplBean pEmpl = getEmpllist().get(getIndex());
			setEmplbean(pEmpl);
			return SUCCESS;
		}
		return INPUT;
	}
	//�༭Ա��
	public String editEmpl() throws Exception{
		getAllEmpllist();
		System.out.println("edit Empl!");
		System.out.println("�༭��Id��"+getEmplbean().getId());
		emplService.updateEmpl(getEmplbean(), getEmplbean().getId());
		getAllEmpllist();
		return SUCCESS;
	}
	//
	public String checkCardId() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String cCardId = getEmplbean().getCardId();
		System.out.println("�����CardId��"+cCardId);
		Boolean isExist = emplService.isCardIdExist(cCardId);
		System.out.println(isExist);
		if(isExist) {
			response.getWriter().println("���֤�Ѵ��ڣ����������룡");
		}else {
			response.getWriter().println("");
		}
		response.getWriter().flush();
		response.getWriter().close();
		return SUCCESS;
	}
	//����ְλ�Ͳ����б�
	public void getJobAndDept() {
		setJoblist(jobService.getJoblist());
		setDeptlist(deptService.getDeptlist());
	}
	//��ȡԱ���б�
	public void getAllEmpllist() {
		getJobAndDept();
/*		List<EmplBean> nEmpllist = emplService.getAllEmpllist();
		setEmpllist(nEmpllist);
		System.out.println("nEmpllist:"+nEmpllist);*/
/*		EmplBean sEmpl = new EmplBean();
		sEmpl.setName(searchName);
		sEmpl.setCardId(searchCardId);
		sEmpl.setTel(searchTel);
		System.out.println("searchDeptname :"+searchDeptname);
		System.out.println("searchJobname :"+searchJobname);
		System.out.println("searchSex :"+searchSex);
		if(searchDeptname>-1)
			sEmpl.setDept(getDeptlist().get(searchDeptname));
		if(searchJobname>-1)
			sEmpl.setJob(getJoblist().get(searchJobname));
		if(searchSex>-1)
			sEmpl.setSex(searchSex);
		System.out.println("sEmpl is "+sEmpl);
		List<EmplBean> nEmpllist = emplService.getEmpllist(sEmpl);
		setEmpllist(nEmpllist);*/
		ActionContext context = ActionContext.getContext();
		EmplBean sEmpl = new EmplBean();
		sEmpl.setName(searchName);
		sEmpl.setCardId(searchCardId);
		sEmpl.setTel(searchTel);
		if(searchDeptname>-1)
			sEmpl.setDept(getDeptlist().get(searchDeptname-1));
		if(searchJobname>-1)
			sEmpl.setJob(getJoblist().get(searchJobname-1));
		if(searchSex>-1)
			sEmpl.setSex(searchSex);
		
		if(getPageNum()==null)
			setPageNum(1);
		Pager<EmplBean> nResult = emplService.findEmpl(sEmpl, getPageNum(), Constant.DEFAULT_PAGE_SIZE);
		setResult(nResult);
		context.getSession().put("emplResult", result);
		setEmpllist(result.getDataList());
		List<EmplBean> nEmpllist = emplService.getEmpllist(sEmpl);
		setEmpllist(nEmpllist);
	//	System.out.println("nEmpllist:"+nEmpllist);
	}
}

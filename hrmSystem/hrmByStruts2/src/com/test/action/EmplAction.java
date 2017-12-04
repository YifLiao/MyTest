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
	private List<JobBean> joblist = new ArrayList<>();     //获取职位列表
	private List<DeptBean> deptlist = new ArrayList<>();   //获取部门列表
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
	//获取员工列表
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
	//删除员工
	public String deleteEmpl() throws Exception {
		ActionContext context = ActionContext.getContext();
		//获取用户状态
		int status = (Integer)context.getSession().get("status");
		System.out.println("删除员工！");
		System.out.println("删除员工的ID："+getIndexs());
		if(status==0&&getIndexs()!=null) {
			//管理员有权限删除员工
			emplService.deleteEmpl(getIndexs());
		}
		return SUCCESS;
	}
	//添加员工页面
	public String toaddEmpl() throws Exception {
		getJobAndDept();
		return SUCCESS;
	}
	//添加员工
	public String addEmpl() throws Exception{
		System.out.println("ADD Empl!");
		emplService.addEmpl(getEmplbean());
		getAllEmpllist();
		return SUCCESS;
	}
	//编辑员工页面
	public String toeditEmpl() throws Exception{
		ActionContext context = ActionContext.getContext();
		//获取用户状态
		int status = (Integer)context.getSession().get("status");
		getAllEmpllist();
		if(status==0) {
			System.out.println("empl编辑的index："+getIndex());
			EmplBean pEmpl = getEmpllist().get(getIndex());
			setEmplbean(pEmpl);
			return SUCCESS;
		}
		return INPUT;
	}
	//编辑员工
	public String editEmpl() throws Exception{
		getAllEmpllist();
		System.out.println("edit Empl!");
		System.out.println("编辑的Id："+getEmplbean().getId());
		emplService.updateEmpl(getEmplbean(), getEmplbean().getId());
		getAllEmpllist();
		return SUCCESS;
	}
	//
	public String checkCardId() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String cCardId = getEmplbean().getCardId();
		System.out.println("输入的CardId："+cCardId);
		Boolean isExist = emplService.isCardIdExist(cCardId);
		System.out.println(isExist);
		if(isExist) {
			response.getWriter().println("身份证已存在！请重新输入！");
		}else {
			response.getWriter().println("");
		}
		response.getWriter().flush();
		response.getWriter().close();
		return SUCCESS;
	}
	//设置职位和部门列表
	public void getJobAndDept() {
		setJoblist(jobService.getJoblist());
		setDeptlist(deptService.getDeptlist());
	}
	//获取员工列表
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

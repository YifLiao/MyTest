package com.test.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.Constant;
import com.test.bean.DeptBean;
import com.test.bean.Pager;
import com.test.service.DeptService;

public class DeptAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int index;
	private List<Integer> indexs;
	private List<DeptBean> deptlist;
	private DeptBean deptbean;
	private String searchName;
	private Integer pageNum;
	private Pager<DeptBean> result; 
	DeptService deptService = new DeptService();

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
	public List<DeptBean> getDeptlist() {
		return deptlist;
	}
	public void setDeptlist(List<DeptBean> deptlist) {
		this.deptlist = deptlist;
	}
	public DeptBean getDeptbean() {
		return deptbean;
	}
	public void setDeptbean(DeptBean deptbean) {
		this.deptbean = deptbean;
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
	
	public Pager<DeptBean> getResult() {
		return result;
	}
	public void setResult(Pager<DeptBean> result) {
		this.result = result;
	}
	//获取部门列表
	public String deptList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("Dept deptlist()");
		//获取列表
		getAllDeptlist();
		List<DeptBean> alldeptlist = getDeptlist();
		context.getSession().put("deptlist", alldeptlist);
		return SUCCESS;
	}
	//删除部门   只对超级用户有效
	public String deleteDept() throws Exception {
		ActionContext context = ActionContext.getContext();
		//获取用户的状态
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		System.out.println("删除的Id："+getIndexs());
		if(status==0&&getIndexs()!=null) {
			deptService.deleteDept(getIndexs());
		}
		return SUCCESS;
	}
	//添加部门
	public String toaddDept() throws Exception{
		return SUCCESS;
	}
	public String addDept() throws Exception{
		System.out.println("ADD Dept!");
		deptService.addDept(getDeptbean());
		getAllDeptlist();
		return SUCCESS;
	}
	//操作编辑部门   超级用户有效
	public String toeditDept() throws Exception{
		ActionContext context = ActionContext.getContext();
		//获取用户的状态
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		if(status==0) {
			System.out.println("dept编辑的index："+getIndex());
			DeptBean pDept = getDeptlist().get(getIndex());
			setDeptbean(pDept);
			return SUCCESS;
		}
		return INPUT;
	}
	public String editDept() throws Exception{
		getAllDeptlist();
		System.out.println("edit Dept!");
		System.out.println("修改的deptbean ID："+getDeptbean().getId());
		//修改部门信息
		deptService.editDept(getDeptbean(), getDeptbean().getId());
		getAllDeptlist();
		return SUCCESS;
	}
	//获取部门列表
	public void getAllDeptlist() {
/*		DeptBean sDept = new DeptBean();
		sDept.setName(searchName);
		List<DeptBean> nDeptlist = deptService.queryDept(sDept);
		setDeptlist(nDeptlist);*/
		ActionContext context = ActionContext.getContext();
		DeptBean sDept = new DeptBean();
		sDept.setName(searchName);
		if(getPageNum()==null)
			setPageNum(1);
		Pager<DeptBean> nResult = deptService.findDept(sDept, getPageNum(), Constant.DEFAULT_PAGE_SIZE);
		setResult(nResult);
		context.getSession().put("deptResult", result);
		setDeptlist(result.getDataList());
		//setDeptlist(deptService.getDeptlist());
	}
	
	
}

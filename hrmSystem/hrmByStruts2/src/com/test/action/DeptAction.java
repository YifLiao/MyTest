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
	//��ȡ�����б�
	public String deptList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("Dept deptlist()");
		//��ȡ�б�
		getAllDeptlist();
		List<DeptBean> alldeptlist = getDeptlist();
		context.getSession().put("deptlist", alldeptlist);
		return SUCCESS;
	}
	//ɾ������   ֻ�Գ����û���Ч
	public String deleteDept() throws Exception {
		ActionContext context = ActionContext.getContext();
		//��ȡ�û���״̬
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		System.out.println("ɾ����Id��"+getIndexs());
		if(status==0&&getIndexs()!=null) {
			deptService.deleteDept(getIndexs());
		}
		return SUCCESS;
	}
	//��Ӳ���
	public String toaddDept() throws Exception{
		return SUCCESS;
	}
	public String addDept() throws Exception{
		System.out.println("ADD Dept!");
		deptService.addDept(getDeptbean());
		getAllDeptlist();
		return SUCCESS;
	}
	//�����༭����   �����û���Ч
	public String toeditDept() throws Exception{
		ActionContext context = ActionContext.getContext();
		//��ȡ�û���״̬
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		if(status==0) {
			System.out.println("dept�༭��index��"+getIndex());
			DeptBean pDept = getDeptlist().get(getIndex());
			setDeptbean(pDept);
			return SUCCESS;
		}
		return INPUT;
	}
	public String editDept() throws Exception{
		getAllDeptlist();
		System.out.println("edit Dept!");
		System.out.println("�޸ĵ�deptbean ID��"+getDeptbean().getId());
		//�޸Ĳ�����Ϣ
		deptService.editDept(getDeptbean(), getDeptbean().getId());
		getAllDeptlist();
		return SUCCESS;
	}
	//��ȡ�����б�
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

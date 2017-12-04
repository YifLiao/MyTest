package com.test.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.Constant;
import com.test.bean.DocumentBean;
import com.test.bean.Pager;
import com.test.service.DocumService;

public class DocumAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int index;
	private List<Integer> indexs;
	private List<DocumentBean> documlist; 
	private DocumentBean documbean;
	private String searchTitle;
	
	private File upload;          //上传文件对象(表单的file name与属性名一致，将上传文件的数据自动注入uploadfile中)
	private String uploadContentType; //自动注入
	private String uploadFileName;    //自动注入
	private String savePath;  
	private String filename;
	private Integer pageNum;
	private Pager<DocumentBean> result;
	DocumService documService = new DocumService();
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
	public List<DocumentBean> getDocumlist() {
		return documlist;
	}
	public void setDocumlist(List<DocumentBean> documlist) {
		this.documlist = documlist;
	}
	public DocumentBean getDocumbean() {
		return documbean;
	}
	public void setDocumbean(DocumentBean documbean) {
		this.documbean = documbean;
	}
	public String getSearchTitle() {
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSavePath() {
		//文件保存路径
		return ServletActionContext.getServletContext().getRealPath("/WEB-INF/"+savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Pager<DocumentBean> getResult() {
		return result;
	}
	public void setResult(Pager<DocumentBean> result) {
		this.result = result;
	}
	//显示文件
	public String documList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("document documList()");
		getAllDeptlist();
		List<DocumentBean> alldocumlist = getDocumlist();
		context.getSession().put("documlist", alldocumlist);
		return SUCCESS;
	}
	//删除文件  只对超级用户有效
	public String deleteDocum() throws Exception{
		ActionContext context = ActionContext.getContext();
		//获取用户状态
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		System.out.println("删除的Id："+getIndexs());
		if(status==0&&getIndexs()!=null) {
			documService.deleteDocum(getIndexs());
		}
		return SUCCESS;
	}
	//上传文件
	public String touploadDocum() throws Exception{
		return SUCCESS;
	}
	public String uploadDocum() throws Exception{
		System.out.println("uploadDocum()");
		System.out.println("文件类型："+getUploadContentType());
		System.out.println("文件名："+getUploadFileName());
		//获取文件的输入流
		FileInputStream fileInputStream = new FileInputStream(upload);
	
		System.out.println(fileInputStream.available());
		//将输入流转为字节数组
		byte[] buff = new byte[fileInputStream.available()];
		fileInputStream.read(buff);
		//保存到DocumentBean的对象
		DocumentBean docum = new DocumentBean();
		docum.setBytes(buff);
		docum.setFileName(uploadFileName);
		docum.setTitle(getDocumbean().getTitle());
		docum.setRemark(getDocumbean().getRemark());
		setDocumbean(docum);
		documService.uploadDocum(getDocumbean());
		//关闭流
		fileInputStream.close();
		getAllDeptlist();
		return SUCCESS;
	}
	//下载文件
	public InputStream getTargetFile() throws Exception{
		getAllDeptlist();
		//获取要下载文件在list里的index
		System.out.println("要下载的文件ID:"+getIndex());
		//获取文件对象
		DocumentBean docum = getDocumlist().get(getIndex());
		//获取文件名
		String pFilename = docum.getFileName();
		System.out.println("rFilename:"+pFilename);
		//解决下载浏览器中文乱码
		setFilename(URLEncoder.encode(pFilename,"UTF-8"));
		//获取字节数组
		byte[] bytes = docum.getBytes();
		System.out.println(bytes.length);
		//转为输入流
		InputStream input = new ByteArrayInputStream(bytes);

		return input;
	}
	//编辑文件
	public String toeditDocum() throws Exception{
		ActionContext context = ActionContext.getContext();
		//获取用户状态
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		if(status==0) {
			System.out.println("需要编辑的文件index："+getIndex());
			//获取未编辑前的内容
			DocumentBean pDocum = getDocumlist().get(getIndex());
			setDocumbean(pDocum);
			return SUCCESS;
		}
		return INPUT;
	}
	public String editDocum() throws Exception {
		getAllDeptlist();
		System.out.println("edit file!");
		documService.editDocum(getDocumbean(), getDocumbean().getId());
		getAllDeptlist();
		return SUCCESS;
	}
	
	//获取文件列表
	public void getAllDeptlist() {
		/*DocumentBean sDocum = new DocumentBean();
		sDocum.setTitle(searchTitle);
		List<DocumentBean> nDocumlist = documService.queryDocum(sDocum);
		setDocumlist(nDocumlist);*/
		ActionContext context = ActionContext.getContext();
		DocumentBean sDocum = new DocumentBean();
		sDocum.setTitle(searchTitle);
		if(getPageNum()==null)
			setPageNum(1);
		Pager<DocumentBean> nResult = documService.findDept(sDocum, getPageNum(), Constant.DEFAULT_PAGE_SIZE);
		setResult(nResult);
		context.getSession().put("documResult", result);
		setDocumlist(result.getDataList());
		//List<DocumentBean> nDocumlist = documService.queryDocum(sDocum);
		//setDocumlist(nDocumlist);
	}

}

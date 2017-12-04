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
	
	private File upload;          //�ϴ��ļ�����(����file name��������һ�£����ϴ��ļ��������Զ�ע��uploadfile��)
	private String uploadContentType; //�Զ�ע��
	private String uploadFileName;    //�Զ�ע��
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
		//�ļ�����·��
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
	//��ʾ�ļ�
	public String documList() throws Exception{
		ActionContext context = ActionContext.getContext();
		System.out.println("document documList()");
		getAllDeptlist();
		List<DocumentBean> alldocumlist = getDocumlist();
		context.getSession().put("documlist", alldocumlist);
		return SUCCESS;
	}
	//ɾ���ļ�  ֻ�Գ����û���Ч
	public String deleteDocum() throws Exception{
		ActionContext context = ActionContext.getContext();
		//��ȡ�û�״̬
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		System.out.println("ɾ����Id��"+getIndexs());
		if(status==0&&getIndexs()!=null) {
			documService.deleteDocum(getIndexs());
		}
		return SUCCESS;
	}
	//�ϴ��ļ�
	public String touploadDocum() throws Exception{
		return SUCCESS;
	}
	public String uploadDocum() throws Exception{
		System.out.println("uploadDocum()");
		System.out.println("�ļ����ͣ�"+getUploadContentType());
		System.out.println("�ļ�����"+getUploadFileName());
		//��ȡ�ļ���������
		FileInputStream fileInputStream = new FileInputStream(upload);
	
		System.out.println(fileInputStream.available());
		//��������תΪ�ֽ�����
		byte[] buff = new byte[fileInputStream.available()];
		fileInputStream.read(buff);
		//���浽DocumentBean�Ķ���
		DocumentBean docum = new DocumentBean();
		docum.setBytes(buff);
		docum.setFileName(uploadFileName);
		docum.setTitle(getDocumbean().getTitle());
		docum.setRemark(getDocumbean().getRemark());
		setDocumbean(docum);
		documService.uploadDocum(getDocumbean());
		//�ر���
		fileInputStream.close();
		getAllDeptlist();
		return SUCCESS;
	}
	//�����ļ�
	public InputStream getTargetFile() throws Exception{
		getAllDeptlist();
		//��ȡҪ�����ļ���list���index
		System.out.println("Ҫ���ص��ļ�ID:"+getIndex());
		//��ȡ�ļ�����
		DocumentBean docum = getDocumlist().get(getIndex());
		//��ȡ�ļ���
		String pFilename = docum.getFileName();
		System.out.println("rFilename:"+pFilename);
		//��������������������
		setFilename(URLEncoder.encode(pFilename,"UTF-8"));
		//��ȡ�ֽ�����
		byte[] bytes = docum.getBytes();
		System.out.println(bytes.length);
		//תΪ������
		InputStream input = new ByteArrayInputStream(bytes);

		return input;
	}
	//�༭�ļ�
	public String toeditDocum() throws Exception{
		ActionContext context = ActionContext.getContext();
		//��ȡ�û�״̬
		int status = (Integer)context.getSession().get("status");
		getAllDeptlist();
		if(status==0) {
			System.out.println("��Ҫ�༭���ļ�index��"+getIndex());
			//��ȡδ�༭ǰ������
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
	
	//��ȡ�ļ��б�
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

package com.test.bean;

import java.util.Arrays;
import java.util.Date;


public class DocumentBean {
	private Integer id;
	private String title;
	private String fileName;
	private String remark;
	private Date createDate;
	private UserBean user;
	private byte[] bytes;


	public DocumentBean() {
		super();
	}

	public DocumentBean(Integer id, String title, String fileName, String remark, Date createDate, UserBean user,  byte[] bytes) {
		super();
		this.id = id;
		this.title = title;
		this.fileName = fileName;
		this.remark = remark;
		this.createDate = createDate;
		this.user = user;
		this.bytes = bytes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		return "DocumentBean [id=" + id + ", title=" + title + ", fileName=" + fileName + ", remark=" + remark
				+ ", createDate=" + createDate + ", user=" + user + ", bytes=" + Arrays.toString(bytes) + "]";
	}
	
	
}

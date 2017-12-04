package com.test.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;
	private String downloadfilePath;
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDownloadfilePath() {
		return downloadfilePath;
	}

	public void setDownloadfilePath(String downloadfilePath) {
		this.downloadfilePath = downloadfilePath;
	}

	public InputStream getTargetFile() throws Exception{
		System.out.println("要下载的文件ID:"+getIndex());
		
		return ServletActionContext.getServletContext().getResourceAsStream(downloadfilePath);
	}
}

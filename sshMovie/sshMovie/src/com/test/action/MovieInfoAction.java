package com.test.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//ӰƬ���޺����۹���
@Controller
@Scope(value="prototype")
public class MovieInfoAction {
	private Integer goodCount;            //���޵Ĵ���
	private String content;               //���۵�����
//	private String moviepath;
	public Integer getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
/*	public String getMoviepath() {
		return moviepath;
	}
	public void setMoviepath(String moviepath) {
		this.moviepath = moviepath;
	}*/
	//����
	public String addGoodCount() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
//		System.out.println("moviepath:"+getMoviepath());
		System.out.println("goodCount"+getGoodCount());
		if(getGoodCount()==null) {
			setGoodCount(0);
		}else
			goodCount = goodCount+1;
		response.getWriter().println(goodCount);
		response.getWriter().flush();
		response.getWriter().close();
		//����Movie���еĵ��޴���
		
		return "addGoodCount";
	}
	
	//����
	public String addComment() throws Exception {
		return "";
	}
	
}

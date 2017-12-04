package com.test.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//影片点赞和评论管理
@Controller
@Scope(value="prototype")
public class MovieInfoAction {
	private Integer goodCount;            //点赞的次数
	private String content;               //评论的内容
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
	//点赞
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
		//更新Movie表中的点赞次数
		
		return "addGoodCount";
	}
	
	//评论
	public String addComment() throws Exception {
		return "";
	}
	
}

package com.test.domain;

import java.util.Date;

public class Comment {
	private String id;            //
	private String movieId;       //��Ӧ��ӰƬ��id
	private String content;        //����
	private Date createDate;       //������ʱ��
	private String creator;        //������
	
	public Comment() {
		super();
	}
	public Comment(String id, String movieId, String content, Date createDate, String creator) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.content = content;
		this.createDate = createDate;
		this.creator = creator;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}

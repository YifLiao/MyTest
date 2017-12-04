package com.test.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Category {
	private String id;
	private String categoryName;   //清单名称
	private Date createDate;       //创建记录时间
	//与Movie的关系是一对多
	private Set<Movie> movielist = new HashSet<>();
	public Category() {
		super();
	}
	public Category(String id, String categoryName, Date createDate, Set<Movie> movielist) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.createDate = createDate;
		this.movielist = movielist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<Movie> getMovielist() {
		return movielist;
	}
	public void setMovielist(Set<Movie> movielist) {
		this.movielist = movielist;
	}
/*	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", createDate=" + createDate + ", movielist="
				+ movielist + "]";
	}*/

}

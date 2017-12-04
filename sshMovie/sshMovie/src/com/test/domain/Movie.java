package com.test.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Movie {
	private String id;
	private String mvName;       //电影名称
	private String mvDesc;       //电影描述
	private String uploader;     //上传者的ID
	private Date uploadTime;     //上传时间
	private Integer playTime;    //播放次数
	private String isEnable;     //是否允许播放
	private Integer goodCount;   //点赞数
	private Category category;     //分类
	private String extName;      //影片拓展名
	private Date createDate;     //创建记录时间
	private String filepath;     //文件路径
	private String filepic;      //发布影片封面路径
	//与comment是一对多的关系
	private Set<Comment> commentlist = new HashSet<>();
	
	public Movie() {
		super();
	}

	public Movie(String id, String mvName, String mvDesc, String uploader, Date uploadTime, Integer playTime,
			String isEnable, Integer goodCount, Category category, String extName, Date createDate, String filepath,
			String filepic, Set<Comment> commentlist) {
		super();
		this.id = id;
		this.mvName = mvName;
		this.mvDesc = mvDesc;
		this.uploader = uploader;
		this.uploadTime = uploadTime;
		this.playTime = playTime;
		this.isEnable = isEnable;
		this.goodCount = goodCount;
		this.category = category;
		this.extName = extName;
		this.createDate = createDate;
		this.filepath = filepath;
		this.filepic = filepic;
		this.commentlist = commentlist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMvName() {
		return mvName;
	}

	public void setMvName(String mvName) {
		this.mvName = mvName;
	}

	public String getMvDesc() {
		return mvDesc;
	}

	public void setMvDesc(String mvDesc) {
		this.mvDesc = mvDesc;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilepic() {
		return filepic;
	}

	public void setFilepic(String filepic) {
		this.filepic = filepic;
	}

	public Set<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(Set<Comment> commentlist) {
		this.commentlist = commentlist;
	}
	
/*	@Override
	public String toString() {
		return "Movie [id=" + id + ", mvName=" + mvName + ", mvDesc=" + mvDesc + ", uploader=" + uploader
				+ ", uploadTime=" + uploadTime + ", playTime=" + playTime + ", isEnable=" + isEnable + ", goodCount="
				+ goodCount + ", category=" + category + ", extName=" + extName + ", createDate=" + createDate
				+ ", filepath=" + filepath + ", filepic=" + filepic + "]";
	}*/


}

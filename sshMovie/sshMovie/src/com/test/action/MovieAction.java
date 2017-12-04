package com.test.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.domain.Category;
import com.test.domain.Movie;
import com.test.domain.Pager;
import com.test.service.ICategoryService;
import com.test.service.IMovieService;
import com.test.util.PageUtil;

@Controller
@Scope(value="prototype")
public class MovieAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private IMovieService movieService;
	private ICategoryService categoryService;
	private Movie movie;
	private List<File> upload;          //上传文件对象(表单的file name与属性名一致，将上传文件的数据自动注入uploadfile中)
	private List<String> uploadContentType; //自动注入
	private List<String> uploadFileName;    //自动注入
	private List<Category> categorylist;
	private Integer cateId;
	
	private String saveFilePath;      //文件的保存路径
	//搜索文件
	private String searchMovie;
	private List<Movie> movielist;
	//文件播放路径
	private String moviepath;
	//当前页码
	private Integer pageNum;
	private Pager<Movie> result;
	//
	private String index;
	private Integer goodCounts;            //点赞的次数
	@Autowired
	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}
	@Autowired
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public List<Category> getCategorylist() {
		return categorylist;
	}
	public void setCategorylist(List<Category> categorylist) {
		this.categorylist = categorylist;
	}
	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	
	public String getSaveFilePath() {
		return ServletActionContext.getServletContext().getRealPath(saveFilePath);
	}
	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}
	public String getSearchMovie() {
		return searchMovie;
	}
	public void setSearchMovie(String searchMovie) {
		this.searchMovie = searchMovie;
	}
	public List<Movie> getMovielist() {
		return movielist;
	}
	public void setMovielist(List<Movie> movielist) {
		this.movielist = movielist;
	}
	public String getMoviepath() {
		return moviepath;
	}
	public void setMoviepath(String moviepath) {
		this.moviepath = moviepath;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Pager<Movie> getResult() {
		return result;
	}
	public void setResult(Pager<Movie> result) {
		this.result = result;
	}
	public Integer getGoodCounts() {
		return goodCounts;
	}
	public void setGoodCounts(Integer goodCounts) {
		this.goodCounts = goodCounts;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	//转发至上传文件页面
	public String toupload() throws Exception {
		categorylist = categoryService.getAllCategory();
		return "toupload";
	}
	//上传影片
	public String upload() throws Exception {
		ActionContext context = ActionContext.getContext();
		String userName = (String)context.getSession().get("user");
		Movie nMovie = new Movie();
		nMovie.setMvName(getMovie().getMvName());           //获取影片名
		nMovie.setMvDesc(getMovie().getMvDesc());           //获取影片描述
		nMovie.setCommentlist(null);                        //影片的评论初始为null
		//类别为空和类别不存在的情况（未写）
		
		//获取影片类别
		//System.out.println("类别："+getMovie().getCategory());
		//nMovie.setCategory(categoryService.getCategory(getMovie().getCategory()));       
		Category category = new Category();
		category.setId(getCateId().toString());
		nMovie.setCategory(categoryService.getCategory(category));
		nMovie.setExtName(getUploadContentType().get(0));   //获取影片的拓展名
		nMovie.setFilepic(getUploadFileName().get(1));      //获取图片封面
		nMovie.setGoodCount(0);                             //初始点赞数为0
		nMovie.setCreateDate(new Date());                   //创建时间为当前时间
		nMovie.setPlayTime(0);                              //初始播放次数为0
		nMovie.setUploadTime(new Date());                   //上传时间为当前时间
		
		//上传者ID
		nMovie.setUploader(userName);
		//获取文件类型和文件名
		System.out.println("getUploadContentType():"+getUploadContentType());
		System.out.println("getUploadFileName():"+getUploadFileName());
		
		//获取视频和封面输入流
		FileInputStream mvIn = new FileInputStream(upload.get(0));
		FileInputStream coverIn = new FileInputStream(upload.get(1));
		
		//将文件上传到某个盘下对应文件夹
		System.out.println("文件保存路径："+getSaveFilePath());
		//获取影片的播放路径
		nMovie.setFilepath("movie"+"\\"+getUploadFileName().get(0));
		//获取影片封面的路径
		nMovie.setFilepic("movie"+"\\"+getUploadFileName().get(1));
		//System.out.println(nMovie);
		movieService.upload(nMovie);
		FileOutputStream mvOut = new FileOutputStream(getSaveFilePath()+"\\"+getUploadFileName().get(0));
		FileOutputStream coverOut = new FileOutputStream(getSaveFilePath()+"\\"+getUploadFileName().get(1));
		byte[] mvBuff = new byte[1024];
		byte[] coverBuff = new byte[1024];
		int mvLen = -1;
		int coverLen = -1;
		while((mvLen=mvIn.read(mvBuff))!=-1) {
			mvOut.write(mvBuff, 0, mvLen);
		}
		while((coverLen=coverIn.read(coverBuff))!=-1) {
			coverOut.write(coverBuff, 0, coverLen);
		}
		
		mvIn.close();
		coverIn.close();
		mvOut.close();
		coverOut.close();
		return "upload";
	}
	//播放影片
	public String playmovie() throws Exception{
		System.out.println("playmovie is :"+getMoviepath());
		System.out.println("movie  is :"+getMovielist());
		
		Movie pMovie = new Movie();
		pMovie.setFilepath(getMoviepath());
		int pGoodCount = movieService.getGoodCountTimes(pMovie);
		setGoodCounts(pGoodCount);
		System.out.println("playmovie goodCounts:"+getGoodCounts());
		
		return "playmovie";
	}

	//搜索影片
	public String tosearchmovie() throws Exception{
		System.out.println("searchMovie:"+getSearchMovie());
		/*List<Movie> nMovielist = movieService.searchMovie(getSearchMovie());
		setMovielist(nMovielist);*/
		//System.out.println(nMovielist);
		System.out.println("getPageNum is :"+getPageNum());
		if(getPageNum()==null) {
			setPageNum(1);
		}
		Pager<Movie> sResult = 
				movieService.queryMovieList(getSearchMovie(), getPageNum(), PageUtil.DEFAULT_PAGE_SIZE);
		setResult(sResult); 
		setMovielist(result.getDataList());
		return "tosearchmovie";
	}
	
	//点赞
	public String addGoodCount() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
//		System.out.println("moviepath:"+getMoviepath());
		System.out.println("addGoodCount********************************");
		System.out.println("goodcount is :"+getGoodCounts());
		System.out.println(getIndex());
		response.getWriter().println(goodCounts);
		response.getWriter().flush();
		response.getWriter().close();
		//更新Movie表中的点赞次数
		movieService.updateGoodCount(1,getIndex());
		return "addGoodCount";
	}
	
}

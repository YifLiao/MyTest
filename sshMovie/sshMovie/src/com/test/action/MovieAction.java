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
	private List<File> upload;          //�ϴ��ļ�����(����file name��������һ�£����ϴ��ļ��������Զ�ע��uploadfile��)
	private List<String> uploadContentType; //�Զ�ע��
	private List<String> uploadFileName;    //�Զ�ע��
	private List<Category> categorylist;
	private Integer cateId;
	
	private String saveFilePath;      //�ļ��ı���·��
	//�����ļ�
	private String searchMovie;
	private List<Movie> movielist;
	//�ļ�����·��
	private String moviepath;
	//��ǰҳ��
	private Integer pageNum;
	private Pager<Movie> result;
	//
	private String index;
	private Integer goodCounts;            //���޵Ĵ���
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
	//ת�����ϴ��ļ�ҳ��
	public String toupload() throws Exception {
		categorylist = categoryService.getAllCategory();
		return "toupload";
	}
	//�ϴ�ӰƬ
	public String upload() throws Exception {
		ActionContext context = ActionContext.getContext();
		String userName = (String)context.getSession().get("user");
		Movie nMovie = new Movie();
		nMovie.setMvName(getMovie().getMvName());           //��ȡӰƬ��
		nMovie.setMvDesc(getMovie().getMvDesc());           //��ȡӰƬ����
		nMovie.setCommentlist(null);                        //ӰƬ�����۳�ʼΪnull
		//���Ϊ�պ���𲻴��ڵ������δд��
		
		//��ȡӰƬ���
		//System.out.println("���"+getMovie().getCategory());
		//nMovie.setCategory(categoryService.getCategory(getMovie().getCategory()));       
		Category category = new Category();
		category.setId(getCateId().toString());
		nMovie.setCategory(categoryService.getCategory(category));
		nMovie.setExtName(getUploadContentType().get(0));   //��ȡӰƬ����չ��
		nMovie.setFilepic(getUploadFileName().get(1));      //��ȡͼƬ����
		nMovie.setGoodCount(0);                             //��ʼ������Ϊ0
		nMovie.setCreateDate(new Date());                   //����ʱ��Ϊ��ǰʱ��
		nMovie.setPlayTime(0);                              //��ʼ���Ŵ���Ϊ0
		nMovie.setUploadTime(new Date());                   //�ϴ�ʱ��Ϊ��ǰʱ��
		
		//�ϴ���ID
		nMovie.setUploader(userName);
		//��ȡ�ļ����ͺ��ļ���
		System.out.println("getUploadContentType():"+getUploadContentType());
		System.out.println("getUploadFileName():"+getUploadFileName());
		
		//��ȡ��Ƶ�ͷ���������
		FileInputStream mvIn = new FileInputStream(upload.get(0));
		FileInputStream coverIn = new FileInputStream(upload.get(1));
		
		//���ļ��ϴ���ĳ�����¶�Ӧ�ļ���
		System.out.println("�ļ�����·����"+getSaveFilePath());
		//��ȡӰƬ�Ĳ���·��
		nMovie.setFilepath("movie"+"\\"+getUploadFileName().get(0));
		//��ȡӰƬ�����·��
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
	//����ӰƬ
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

	//����ӰƬ
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
	
	//����
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
		//����Movie���еĵ��޴���
		movieService.updateGoodCount(1,getIndex());
		return "addGoodCount";
	}
	
}

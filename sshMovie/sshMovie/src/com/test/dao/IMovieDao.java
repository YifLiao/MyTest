package com.test.dao;

import java.util.List;

import com.test.domain.Movie;
import com.test.domain.Pager;

public interface IMovieDao {

	//上传视频
	public void upload(Movie movie);
	//获取视频列表
	public List<Movie> getmovielist();
	//搜索视频
	public List<Movie> searchMovie(String searchMovie);
	//获取视频的总记录数
	public long getTotalCount(String searchMovie);
	//分页查询  
	public Pager<Movie> queryMovieList(String searchMovie,int pageNum,int pageSize);
	//获取点赞的次数
	public int getGoodCountTimes(Movie movie);
	//更新点赞的次数
	public void updateGoodCount(int nGoodCount,String moviePath);
}

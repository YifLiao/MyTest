package com.test.service;

import java.util.List;

import com.test.domain.Movie;
import com.test.domain.Pager;

public interface IMovieService {
	public void upload(Movie movie);
	public List<Movie> getMovielist();
	public List<Movie> searchMovie(String searchMovie);
	public Pager<Movie> queryMovieList(String searchMovie,int pageNum,int pageSize);
	public int getGoodCountTimes(Movie movie);
	public void updateGoodCount(int nGoodCount,String moviePath);
}

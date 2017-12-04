package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.IMovieDao;
import com.test.domain.Movie;
import com.test.domain.Pager;
import com.test.service.IMovieService;

@Service
public class MovieService implements IMovieService{
	
	private IMovieDao movieDao;
	
	@Autowired
	public void setMovieDao(IMovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	@Transactional(readOnly=false)
	@Override
	public void upload(Movie movie) {
		// TODO Auto-generated method stub
		movieDao.upload(movie);
	}

	@Override
	public List<Movie> getMovielist() {
		// TODO Auto-generated method stub
		List<Movie> movielist = movieDao.getmovielist();
		return movielist;
	}

	@Override
	public List<Movie> searchMovie(String searchMovie) {
		// TODO Auto-generated method stub
		if(searchMovie==null||searchMovie.trim().equals(""))
			return null;
		return movieDao.searchMovie(searchMovie);
	}

	@Override
	public Pager<Movie> queryMovieList(String searchMovie, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		/*if(searchMovie==null||searchMovie.trim().equals("")) {
			return null;
		}*/
		return movieDao.queryMovieList(searchMovie, pageNum, pageSize);
	}

	@Override
	public int getGoodCountTimes(Movie movie) {
		// TODO Auto-generated method stub
		
		return movieDao.getGoodCountTimes(movie);
	}

	@Transactional(readOnly=false)
	@Override
	public void updateGoodCount(int nGoodCount, String moviePath) {
		// TODO Auto-generated method stub
		movieDao.updateGoodCount(nGoodCount, moviePath);
	}

}

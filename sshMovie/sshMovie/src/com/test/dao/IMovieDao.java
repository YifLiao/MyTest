package com.test.dao;

import java.util.List;

import com.test.domain.Movie;
import com.test.domain.Pager;

public interface IMovieDao {

	//�ϴ���Ƶ
	public void upload(Movie movie);
	//��ȡ��Ƶ�б�
	public List<Movie> getmovielist();
	//������Ƶ
	public List<Movie> searchMovie(String searchMovie);
	//��ȡ��Ƶ���ܼ�¼��
	public long getTotalCount(String searchMovie);
	//��ҳ��ѯ  
	public Pager<Movie> queryMovieList(String searchMovie,int pageNum,int pageSize);
	//��ȡ���޵Ĵ���
	public int getGoodCountTimes(Movie movie);
	//���µ��޵Ĵ���
	public void updateGoodCount(int nGoodCount,String moviePath);
}

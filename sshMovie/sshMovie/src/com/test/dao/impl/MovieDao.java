package com.test.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.dao.IMovieDao;
import com.test.domain.Movie;
import com.test.domain.Pager;    

@Repository
public class MovieDao extends HibernateDaoSupport implements IMovieDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);  
	}
	
	@Override
	public void upload(Movie movie) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(movie);
	}

	@Override
	public List<Movie> getmovielist() {
		// TODO Auto-generated method stub
		/*List<Movie> movielist = (List<Movie>) getHibernateTemplate().find("from Movie");
		return movielist;*/
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> searchMovie(String searchMovie) {
		// TODO Auto-generated method stub
		List<Movie> movielist = new ArrayList<>();
		movielist = (List<Movie>) getHibernateTemplate().find(" from Movie m where m.mvName like ?", "%"+searchMovie+"%");
		return movielist;
	}

	@Override
	public long getTotalCount(String searchMovie) {
		// TODO Auto-generated method stub
		//hql语句   模糊查询电影   获取总记录数
		String hql="select count(*) from Movie m where m.mvName like '%"+searchMovie+"%'";
		//List<?> countlist = getHibernateTemplate().find(hql);
		@SuppressWarnings("unchecked")
		List<Long> countlist=(List<Long>) getHibernateTemplate().find(hql);
		return countlist.get(0);
	}

	/*
	 * pageNum:当前页数
	 * pageSize:每页显示的记录数  DEFAULT_PAGE_SIZE
	 * */
	@Override
	public Pager<Movie> queryMovieList(String searchMovie,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		//查看记录条数
		//System.out.println(getTotalCount(searchMovie));
		Pager<Movie> result = null;
		//获取总记录数
		int totalRecord = (int) getTotalCount(searchMovie);
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		String hql="from Movie m where m.mvName like '%"+searchMovie+"%'";
		//session   回调机制
		List<Movie> movielist = getHibernateTemplate().execute(new HibernateCallback<List<Movie>>() {
			@Override
			public List<Movie> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				int fromIndex	= pageSize * (pageNum -1); 
				@SuppressWarnings("unchecked")
				List<Movie> movielist = session.createQuery(hql).setFirstResult(fromIndex).setMaxResults(pageSize).list();
				return movielist;
			}
		});
		result = new Pager<Movie>(pageSize, pageNum, 
				totalRecord, totalPage, movielist);
		return result;
	}

	@Override
	public int getGoodCountTimes(Movie movie) {
		// TODO Auto-generated method stub
		List<Movie> movielist = getHibernateTemplate().findByExample(movie);
		return movielist.get(0).getGoodCount();
	}

	@Override
	public void updateGoodCount(int nGoodCount, String moviePath) {
		// TODO Auto-generated method stub
		Movie movie = (Movie)getHibernateTemplate().get(Movie.class, moviePath);
		movie.setGoodCount(nGoodCount);
		getHibernateTemplate().update(movie);
	}

}

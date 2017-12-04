package com.test.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.dao.ICategoryDao;
import com.test.domain.Category;

@Repository
public class CategoryDao extends HibernateDaoSupport implements ICategoryDao{
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);  
	}

	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(category);
	}

	@Override
	public Category getCategory(Category category) {
		// TODO Auto-generated method stub
		Category outCategory = getHibernateTemplate().findByExample(category).get(0);
		return outCategory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return (List<Category>) getHibernateTemplate().find("from Category");
	}
	
}

package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.ICategoryDao;
import com.test.domain.Category;
import com.test.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	private ICategoryDao categoryDao;

	@Autowired
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.saveCategory(category);
	}

	@Override
	public Category getCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.getCategory(category);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryDao.getAllCategory();
	}
	
	
}

package com.test.dao;

import java.util.List;

import com.test.domain.Category;

public interface ICategoryDao {
	
	//保存类别
	public void saveCategory(Category category);
	//获取类别
	public Category getCategory(Category category);
	public List<Category> getAllCategory();
}

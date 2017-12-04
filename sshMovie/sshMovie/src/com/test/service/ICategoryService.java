package com.test.service;

import java.util.List;

import com.test.domain.Category;

public interface ICategoryService {
	
	public void saveCategory(Category category);
	public Category getCategory(Category category);
	public List<Category> getAllCategory();
}

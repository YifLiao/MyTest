package com.test.dao;

import java.util.List;

import com.test.domain.Category;

public interface ICategoryDao {
	
	//�������
	public void saveCategory(Category category);
	//��ȡ���
	public Category getCategory(Category category);
	public List<Category> getAllCategory();
}

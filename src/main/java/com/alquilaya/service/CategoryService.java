package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.ICategoryDao;
import com.alquilaya.entity.Category;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	ICategoryDao dao;

	@Override
	public Category saveCategory(Category category) {

		return dao.saveCategory(category);
	}

	@Override
	public List<Category> findAllCategories() {

		return dao.findAllCategories();
	}

	@Override
	public Optional<Category> findCategoryById(Integer id) {

		return dao.findCategoryById(id);
	}

	@Override
	public Category updateCategory(Category category, Integer id) {
		
		return dao.updateCategory(category, id);
	}

	@Override
	public Category deleteCategory(Integer id) {
		
		return dao.deleteCategory(id);
	}

}

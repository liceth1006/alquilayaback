package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Category;

public interface ICategoryDao {
	Category saveCategory(Category category);
    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Integer id);
    Category updateCategory(Category category, Integer id);
    Category deleteCategory(Integer id);
}

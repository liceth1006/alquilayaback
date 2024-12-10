package com.alquilaya.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Category;
import com.alquilaya.jpa.ICategoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class CategoryDao  implements ICategoryDao{

	
	@Autowired
	ICategoryJpa jpa;
	
	
	@Override
	public Category saveCategory(Category category) {
		return jpa.save(category);
	}

	@Override
	public List<Category> findAllCategories() {
		return jpa.findAll();
	}

	@Override
	public Optional<Category> findCategoryById(Integer id) {
		return jpa.findById(id);
	}

	@Override
	public Category updateCategory(Category category, Integer id) {
		if (jpa.existsById(id)) {
			category.setCategoryId(id);  
            return jpa.save(category);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

	@Override
	public Category deleteCategory(Integer id) {
		Category category = jpa.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
category.setStatus(!category.isStatus());
return jpa.save(category);
		
	}
	

}

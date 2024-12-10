package com.alquilaya.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilaya.entity.Category;

public interface ICategoryJpa extends JpaRepository<Category, Integer> {

}

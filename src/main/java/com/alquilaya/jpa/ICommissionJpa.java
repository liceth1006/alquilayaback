package com.alquilaya.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilaya.entity.Commission;

public interface ICommissionJpa extends JpaRepository<Commission, Integer> {

}

package com.alquilaya.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilaya.entity.Phone;

public interface IPhoneJpa extends JpaRepository<Phone, Integer>  {

}

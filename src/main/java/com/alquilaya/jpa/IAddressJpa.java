package com.alquilaya.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilaya.entity.Address;

public interface IAddressJpa extends JpaRepository<Address, Integer>{

}

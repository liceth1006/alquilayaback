package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Address;

public interface IAddressDao {
	Address saveAddress(Address address); 
	 List<Address> findAllAddresses();    
	 Optional<Address> findAddressById(Integer id);
	 Address updateAddress(Address address, Integer id);
}

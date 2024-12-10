package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Address;
import com.alquilaya.jpa.IAddressJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class AddressDao  implements IAddressDao{
	
	@Autowired
	IAddressJpa jpa;

	@Override
	public Address saveAddress(Address address) {
		return jpa.save(address);
	}

	@Override
	public List<Address> findAllAddresses() {
		return jpa.findAll();
	}

	@Override
	public Optional<Address> findAddressById(Integer id) {
		 return jpa.findById(id);
	}

	@Override
	public Address updateAddress(Address address, Integer id) {
		if (jpa.existsById(id)) {
            address.setAddressId(id);  
            return jpa.save(address);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }
	

}

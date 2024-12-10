package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IAddressDao;
import com.alquilaya.entity.Address;

@Service
public class AddressService implements IAddressService{

	@Autowired
	IAddressDao dao;
	
	@Override
	public Address saveAddress(Address address) {
		
		return dao.saveAddress(address);
	}

	@Override
	public List<Address> findAllAddresses() {
		
		return dao.findAllAddresses();
	}

	@Override
	public Optional<Address> findAddressById(Integer id) {
		
		return dao.findAddressById(id);
	}

	@Override
	public Address updateAddress(Address address, Integer id) {
		
		return dao.updateAddress(address, id);
	}

}

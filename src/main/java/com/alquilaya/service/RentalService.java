package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IRentalDao;
import com.alquilaya.entity.Rental;

@Service
public class RentalService  implements IRentalService{

	@Autowired
	IRentalDao dao;
	@Override
	public Rental saveRental(Rental rental) {
		return dao.saveRental(rental);
	}

	@Override
	public List<Rental> findAllRentals() {
		return dao.findAllRentals();
	}

	@Override
	public Optional<Rental> findRentalById(Integer id) {
		return dao.findRentalById(id);
	}

	@Override
	public List<Rental> findRentalsBySupplierId(Integer supplierId) {
		return dao.findBySupplierId(supplierId);
	}

	@Override
	public List<Rental> findRentalsByProductId(Integer productId) {
		return dao.findRentalsByProductId(productId);
	}

	@Override
	public Rental updateRental(Rental rental, Integer id) {
		return dao.updateRental(rental, id);
	}

}

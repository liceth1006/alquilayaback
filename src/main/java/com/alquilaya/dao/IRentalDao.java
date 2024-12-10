package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Rental;

public interface IRentalDao {
	Rental saveRental(Rental rental);
    List<Rental> findAllRentals();
    Optional<Rental> findRentalById(Integer id);
    List<Rental> findBySupplierId(Integer supplierId);
    List<Rental> findRentalsByProductId(Integer productId);
    Rental updateRental(Rental rental, Integer id);
}

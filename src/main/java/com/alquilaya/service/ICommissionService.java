package com.alquilaya.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Commission;

public interface ICommissionService {
	
	Commission saveCommission(Commission commission);
	List<Commission> findAllCommissions();
	Optional<Commission> findCommissionById(Integer id);
	Commission updateCommission(Commission commission, Integer id);
	BigDecimal calculateCommission(BigDecimal amount, BigDecimal rate);
}

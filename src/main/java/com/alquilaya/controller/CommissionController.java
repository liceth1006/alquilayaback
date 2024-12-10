package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.entity.Commission;
import com.alquilaya.service.ICommissionService;

import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/v1/commissions")
@Tag(name = "15 - Comision", description = "Operaciones relacionadas con .")
public class CommissionController {
	  @Autowired
	    ICommissionService service;

	    @GetMapping(value="/adm/commissions-all", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Commission>> getAllCommissions() {
	        List<Commission> commissions = service.findAllCommissions();
	        return new ResponseEntity<>(commissions, HttpStatus.OK);
	    }

	    @GetMapping(value = "/adm/comissions-id", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Commission> getCommissionById(@PathVariable Integer id) {
	        Optional<Commission> commission = service.findCommissionById(id);
	        return commission.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PostMapping(value="admin/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Commission> createCommission(@RequestBody Commission commission) {
	        Commission newCommission = service.saveCommission(commission);
	        return new ResponseEntity<>(newCommission, HttpStatus.CREATED);
	    }

	    @PutMapping(value = "/admin/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Commission> updateCommission(@PathVariable Integer id, @RequestBody Commission commission) {
	        Commission updatedCommission = service.updateCommission(commission, id);
	        return new ResponseEntity<>(updatedCommission, HttpStatus.OK);
	    }

	    /*
	    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<BigDecimal> calculateCommission(@RequestBody CommissionCalculationRequest request) {
	        BigDecimal commission = service.calculateCommission(request.getAmount(), request.getRate());
	        return new ResponseEntity<>(commission, HttpStatus.OK);
	    }
	    */
}

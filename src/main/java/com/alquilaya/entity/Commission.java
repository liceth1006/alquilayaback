package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the commission database table.
 * 
 */
@Entity
@NamedQuery(name="Commission.findAll", query="SELECT c FROM Commission c")
public class Commission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="commission_id")
	private int commissionId;

	@Column(name="commission_amount")
	@Pattern(regexp = "^\\d{1,10}(\\.\\d{2})?$", message = "permite un número con hasta 10 dígitos enteros, seguido de un punto decimal y exactamente dos dígitos decimales.")
	private BigDecimal commissionAmount;

	@Column(name="commission_percentage")
	@Pattern(regexp = "^\\d{1,5}(\\.\\d{2})?$", message = "permite un número con hasta 5 dígitos enteros, seguido de un punto decimal y exactamente dos dígitos decimales")
	private BigDecimal commissionPercentage;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Rental
	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental rental;

	public Commission() {
	}

	public int getCommissionId() {
		return this.commissionId;
	}

	public void setCommissionId(int commissionId) {
		this.commissionId = commissionId;
	}

	public BigDecimal getCommissionAmount() {
		return this.commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public BigDecimal getCommissionPercentage() {
		return this.commissionPercentage;
	}

	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Rental getRental() {
		return this.rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

}
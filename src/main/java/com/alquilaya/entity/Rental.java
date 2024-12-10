package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the rental database table.
 * 
 */
@Entity
@NamedQuery(name="Rental.findAll", query="SELECT r FROM Rental r")
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id")
	private int rentalId;

	@Column(name="commission_amount")
	@Pattern(regexp = "^\\d{1,10}(\\.\\d{2})?$", message = "permite un número con hasta 10 dígitos enteros, seguido de un punto decimal y exactamente dos dígitos decimales.")
	private BigDecimal commissionAmount;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="delivery_date")
	private Timestamp deliveryDate;

	@Column(name="rental_status")
	@Pattern(regexp = "^[0-1]$", message = "Puede contener 0 o 1")
	private String rentalStatus;

	@Column(name="return_date")
	private Timestamp returnDate;

	@Column(name="supplier_id")
	private int supplierId;

	@Column(name="total_amount")
	@Pattern(regexp = "^\\d{1,10}(\\.\\d{2})?$", message = "permite un número con hasta 10 dígitos enteros, seguido de un punto decimal y exactamente dos dígitos decimales.")
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to Commission
	@OneToMany(mappedBy="rental")
	@JsonIgnoreProperties(value = "rental")
	private List<Commission> commissions;

	//bi-directional many-to-one association to OrderDetail
	@ManyToOne
	@JoinColumn(name="order_detail_id")
	private OrderDetail orderDetail;

	public Rental() {
	}

	public int getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public BigDecimal getCommissionAmount() {
		return this.commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getRentalStatus() {
		return this.rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public Timestamp getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Commission> getCommissions() {
		return this.commissions;
	}

	public void setCommissions(List<Commission> commissions) {
		this.commissions = commissions;
	}

	/*public Commission addCommission(Commission commission) {
		getCommissions().add(commission);
		commission.setRental(this);

		return commission;
	}

	public Commission removeCommission(Commission commission) {
		getCommissions().remove(commission);
		commission.setRental(null);

		return commission;
	}

	public OrderDetail getOrderDetail() {
		return this.orderDetail;
	}*/

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

}
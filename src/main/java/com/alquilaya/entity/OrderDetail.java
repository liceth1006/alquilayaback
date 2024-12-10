package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the order_details database table.
 * 
 */
@Entity
@Table(name="order_details")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	private int orderDetailId;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="order_id")
	private int orderId;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="orderDetail")
	private List<Rental> rentals;

	public OrderDetail() {
	}

	public int getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setOrderDetail(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setOrderDetail(null);

		return rental;
	}

}
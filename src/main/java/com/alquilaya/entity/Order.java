package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	@Column(name="order_date")
	
	private Timestamp orderDate;

	private String status;

	@Column(name="user_id")
	private int userId;

	public Order() {
	}

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
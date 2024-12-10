package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the product_details database table.
 * 
 */

@Entity
@Table(name = "product_details")
@NamedQuery(name = "ProductDetails.findAll", query = "SELECT pr FROM ProductDetails pr")
public class ProductDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_details_id")
	private int productDetailsId;

	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,#-]{1,100}$", message = "La linea de dirección puede contener letras, números, espacios, #, - ,comas y puntos")
	@NotBlank(message = "La dirección es obligatoria")
	private String address;


	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ]{1,255}$", message = "La ciudad solo puede contener letras, números y espacios, y debe tener entre 1 y 255 caracteres")
	@Column(name = "city")
	private String city;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private Timestamp createdAt;

	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ]{1,255}$", message = "El departamento solo puede contener letras, números y espacios, y debe tener entre 1 y 255 caracteres")
	private String department;


	// bi-directional many-to-one association to Product
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	//@Pattern(regexp = "^[0-9 ]{1,11}$", message = "El Stock solo puede contener números y espacios, y debe tener entre 1 y 11 caracteres")
	@Min(value = 1, message = "El Stock debe ser al menos 1")
	private int stock;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "updated_at",updatable = false)
	private Timestamp updatedAt;

	public ProductDetails() {
	}

	public int getProductDetailsId() {
		return this.productDetailsId;
	}

	public void setProductDetailsId(int productDetailsId) {
		this.productDetailsId = productDetailsId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
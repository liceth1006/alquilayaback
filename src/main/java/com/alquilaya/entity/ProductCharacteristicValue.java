package com.alquilaya.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;


/**
 * The persistent class for the product_characteristic_value database table.
 * 
 */
@Entity
@Table(name="product_characteristic_value")
@NamedQuery(name="ProductCharacteristicValue.findAll", query="SELECT p FROM ProductCharacteristicValue p")
public class ProductCharacteristicValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="value_id")
	private int valueId;

	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El value solo puede contener letras, números y espacios, y debe tener entre 1 y 250 caracteres")
	private String value;



	//bi-directional many-to-one association to ProductCharacteristic
	@ManyToOne
	@JoinColumn(name="characteristic_id")
	private ProductCharacteristic productCharacteristic;
	
	//bi-directional many-to-one association to Product
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public ProductCharacteristicValue() {
	}

	public int getValueId() {
		return this.valueId;
	}

	public void setValueId(int valueId) {
		this.valueId = valueId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductCharacteristic getProductCharacteristic() {
		return this.productCharacteristic;
	}

	public void setProductCharacteristic(ProductCharacteristic productCharacteristic) {
		this.productCharacteristic = productCharacteristic;
	}

}
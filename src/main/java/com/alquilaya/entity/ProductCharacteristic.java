package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;




/**
 * The persistent class for the product_characteristic database table.
 * 
 */
@Entity
@Table(name="product_characteristic")
@NamedQuery(name="ProductCharacteristic.findAll", query="SELECT p FROM ProductCharacteristic p")
public class ProductCharacteristic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="characteristic_id")
	private int characteristicId;

	@Column(name="characteristic_name")
	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El nombre solo puede contener letras, números y espacios, y debe tener entre 1 y 250 caracteres")
	private String characteristicName;



	public ProductCharacteristic() {
	}

	public int getCharacteristicId() {
		return this.characteristicId;
	}

	public void setCharacteristicId(int characteristicId) {
		this.characteristicId = characteristicId;
	}

	public String getCharacteristicName() {
		return this.characteristicName;
	}

	public void setCharacteristicName(String characteristicName) {
		this.characteristicName = characteristicName;
	}




}
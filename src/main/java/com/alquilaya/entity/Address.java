package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private int addressId;

	@Column(name="address_line")
	@Pattern(
		    regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ.,#\\-\\s]{1,255}$", 
		    message = "La dirección solo puede contener letras, números, espacios, tildes, comas, puntos, el símbolo #, y guiones, y debe tener entre 1 y 255 caracteres"
		)
	private String addressLine;

	@Column(name="address_type")
	@Pattern(
		    regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ.,#\\-\\s]{1,255}$", 
		    message = "El tipo de dirección solo puede contener letras, números, espacios, tildes, comas, puntos, el símbolo #, y guiones, y debe tener entre 1 y 255 caracteres"
		)
	private String addressType;

	@Pattern(
		    regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ.,#\\-\\s]{1,255}$", 
		    message = "La ciudad solo puede contener letras, números, espacios, tildes, comas, puntos, el símbolo #, y guiones, y debe tener entre 1 y 255 caracteres"
		)
	private String city;

	@Column(name="created_at")
	private Timestamp createdAt;
	
	
	@Column(name="is_primary")
	private byte isPrimary;

	private byte status = 1;


	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Profile
	
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;

	public Address() {
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine() {
		return this.addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
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

	public byte getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(byte isPrimary) {
		this.isPrimary = isPrimary;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	

}
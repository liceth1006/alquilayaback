package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

/**
 * The persistent class for the phone database table.
 * 
 */
@Entity
@NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_id")
	private int phoneId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "is_primary")
	private byte isPrimary;

	@Column(name = "phone_number")
	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ., ]{1,255}$", message = "El telefono solo puede contener letras, números, espacios, tildes, comas, y puntos, y debe tener entre 1 y 255 caracteres")
	private String phoneNumber;

	@Column(name = "phone_type")
	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ.,#\\-\\s]{1,255}$", message = "El tipo de teléfono solo puede contener letras, números, espacios, tildes, comas, puntos, el símbolo #, y guiones, y debe tener entre 1 y 255 caracteres")
	private String phoneType;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	// bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public Phone() {
	}

	public int getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
}
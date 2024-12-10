package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ., ]{1,255}$", message = "La descripción solo puede contener letras, números, espacios, tildes, comas, y puntos, y debe tener entre 1 y 255 caracteres")
	private String description;

	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ ]{1,255}$", message = "El nombre solo puede contener letras, números, espacios y tildes, y debe tener entre 1 y 255 caracteres")
	private String name;

	private boolean status = true;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
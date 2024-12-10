package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the product_photo database table.
 * 
 */
@Entity
@Table(name="product_photo")
@NamedQuery(name="ProductPhoto.findAll", query="SELECT p FROM ProductPhoto p")
public class ProductPhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="photo_id")
	private int photoId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="is_primary")
	//@Pattern(regexp = "^[0-1]$", message = "Puede contener 0 o 1")
	private byte isPrimary;

	@Column(name="photo_url")
	private String photoUrl;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	
	//bi-directional many-to-one association to Product
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public ProductPhoto() {
	}

	public int getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
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

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the favorites database table.
 * 
 */
@Entity
@Table(name = "favorites")
@NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favorite_id")
	private int favoriteId;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Favorite() {
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "product_condition")
	private String productCondition;

	@Temporal(TemporalType.TIMESTAMP)
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	@Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ¡¿\\s]+$", message = "La descripción solo puede contener letras, números, tildes, eñes y espacios.")
	@Size(max = 250, message = "La descripción no puede exceder los 250 caracteres")
	private String description;

	@Column(name = "is_available")
	private byte isAvailable;

	@NotNull(message = "El nombre del producto es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras, números, tildes, eñes y espacios.")
    private String name;

	private BigDecimal price;

	private byte status;
	
	
	@JsonIgnoreProperties({ "createdAt", "email", "isVerified", "password", "role", "status", 
        "statusDocument", "updatedAt", "profile" })
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supplier_id")
	private User supplier;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	// bi-directional many-to-one association to ProductPhoto
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductPhoto> productPhotos;

	// bi-directional many-to-one association to
	@JsonManagedReference
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductDetails> productDetails;
	
	@JsonBackReference
	@OneToMany(mappedBy = "product")
	private List<Favorite> favorites;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCondition() {
		return productCondition;
	}

	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
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

	public byte getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public User getSupplier() {
		return supplier;
	}

	public void setSupplier(User supplier) {
		this.supplier = supplier;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductPhoto> getProductPhotos() {
		return productPhotos;
	}

	public void setProductPhotos(List<ProductPhoto> productPhotos) {
		this.productPhotos = productPhotos;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	
	

	
}
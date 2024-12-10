package com.alquilaya.dto;

import java.math.BigDecimal;

public class ProductProjectionDTO {
	private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private byte isAvailable;
    private String productCondition;
    private byte status;
    private String categoryName;
    private int categoryId;
    private String supplierName;  
    private String primaryPhotoUrl;
    
	
	
	public ProductProjectionDTO(int productId, String name, String description, BigDecimal price, byte isAvailable,
			String productCondition, byte status, String categoryName, int categoryId, String supplierName,
			String primaryPhotoUrl) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.productCondition = productCondition;
		this.status = status;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.supplierName = supplierName;
		this.primaryPhotoUrl = primaryPhotoUrl;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public byte getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getProductCondition() {
		return productCondition;
	}
	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getPrimaryPhotoUrl() {
		return primaryPhotoUrl;
	}
	public void setPrimaryPhotoUrl(String primaryPhotoUrl) {
		this.primaryPhotoUrl = primaryPhotoUrl;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
    
    

}

package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Timestamp createdAt;

	private String email;

	
	
	@Column(name="firebase_uid")
	private String firebaseUid;
	
	//@Pattern(regexp = "^(customer|supplier|admin)$", message = "intente de nuevo")
	private String role ="CUSTOMER";
	
	//@Pattern(regexp = "^[0-1]$", message = "Puede contener 0 o 1")
	private byte status;

	@Column(name="status_document")
	//@Pattern(regexp = "^[0-1]$", message = "Puede contener 0 o 1")
	private String statusDocument = "pending";

	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Timestamp updatedAt;
	
	@OneToMany(mappedBy = "user")
	@JsonBackReference
    private List<Favorite> favorites;

	@OneToOne(mappedBy = "user")
	@JsonIgnoreProperties(value = "user")
	private Profile profile;

	public User() {
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getStatusDocument() {
		return this.statusDocument;
	}

	public void setStatusDocument(String statusDocument) {
		this.statusDocument = statusDocument;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirebaseUid() {
		return firebaseUid;
	}

	public void setFirebaseUid(String firebaseUid) {
		this.firebaseUid = firebaseUid;
	}
	

}
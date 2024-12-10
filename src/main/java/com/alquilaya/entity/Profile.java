package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_id")
	private int profileId;
	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "La biografia solo puede contener letras, números y espacios, y debe tener entre 1 y 250 caracteres")
	private String bio;

	@Temporal(TemporalType.TIMESTAMP)

	// @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
	// message = "El formato esta incorrecto, intente de nuevo")
	@Column(name = "birth_date")

	private Date birthDate;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "document_number")
	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El documento solo puede contener letras, números y espacios, y debe tener entre 1 y 250 caracteres")
	private String documentNumber;

	@Column(name = "first_name")
	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El nombre solo puede contener letras, números y espacios, y debe tener entre 1 y 250 caracteres")
	private String firstName;

	@Column(name = "last_name")
	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El Apellido solo puede contener letras, números y espacios, y debe tener entre 1 y 250 caracteres")
	private String lastName;

	private String photo;

	@ManyToOne
	@JoinColumn(name = "type_document_id", referencedColumnName = "type_document_id", insertable = false, updatable = false)
	private TypeDocument typeDocument;

	 @Column(name = "type_document_id") 
	    private int typeDocumentId;
	 
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	 // bi-directional one-to-many association to Phone
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "profile")
    private List<Phone> phones;

	public Profile() {
	}

	public int getProfileId() {
		return this.profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDocumentNumber() {
		return this.documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public TypeDocument getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTypeDocumentId() {
		return typeDocumentId;
	}

	public void setTypeDocumentId(int typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}

}
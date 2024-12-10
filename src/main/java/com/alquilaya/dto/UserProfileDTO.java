package com.alquilaya.dto;

import java.sql.Timestamp;
import java.util.Date;

public class UserProfileDTO {
    private int userId;
    private String email;
    private String role;
    private byte status;
    private String statusDocument;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private int profileId;
    private String bio;
    private Date birthDate;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String photo;
    private int typeDocumentId;
   // private int documentName;
    
	public UserProfileDTO() {
		super();
	}



	public UserProfileDTO(int userId, String email, String role, byte status, String statusDocument,
			Timestamp createdAt, Timestamp updatedAt, int profileId, String bio, Date birthDate, String documentNumber,
			String firstName, String lastName, String photo, int typeDocumentId) {
		super();
		this.userId = userId;
		this.email = email;
		this.role = role;
		this.status = status;
		this.statusDocument = statusDocument;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.profileId = profileId;
		this.bio = bio;
		this.birthDate = birthDate;
		this.documentNumber = documentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
		this.typeDocumentId = typeDocumentId;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getStatusDocument() {
		return statusDocument;
	}

	public void setStatusDocument(String statusDocument) {
		this.statusDocument = statusDocument;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getTypeDocumentId() {
		return typeDocumentId;
	}

	public void setTypeDocumentId(int typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}


	

    
    
    
	
}

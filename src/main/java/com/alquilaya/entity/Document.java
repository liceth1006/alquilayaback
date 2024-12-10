package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;


/**
 * The persistent class for the document database table.
 * 
 */
@Entity
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="document_id")
	private int documentId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="document_number")
	private String documentNumber;

	@Column(name="document_status")
	private String documentStatus;

	@Column(name="document_url")
	private String documentUrl;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="upload_date")
	private Timestamp uploadDate;

	@Column(name="verified_at")
	private Timestamp verifiedAt;

	@Column(name="verified_by")
	private int verifiedBy;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;

	
	//bi-directional many-to-one association to TypeDocument
	@ManyToOne
	@JoinColumn(name="type_document_id")
	private TypeDocument typeDocument;

	public Document() {
	}

	public int getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
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

	public String getDocumentStatus() {
		return this.documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getDocumentUrl() {
		return this.documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Timestamp getVerifiedAt() {
		return this.verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public int getVerifiedBy() {
		return this.verifiedBy;
	}

	public void setVerifiedBy(int verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public TypeDocument getTypeDocument() {
		return this.typeDocument;
	}

	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}

}
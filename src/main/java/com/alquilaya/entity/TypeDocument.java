package com.alquilaya.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.List;


/**
 * The persistent class for the type_document database table.
 * 
 */
@Entity
@Table(name="type_document")
@NamedQuery(name="TypeDocument.findAll", query="SELECT t FROM TypeDocument t")
public class TypeDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="type_document_id")
	private int typeDocumentId;

	@Column(name="document_name")
	@Pattern(
		    regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚüÜ.,#\\-\\s]{1,255}$", 
		    message = "El tipo de documento solo puede contener letras, números, espacios, tildes, comas, puntos, el símbolo #, y guiones, y debe tener entre 1 y 255 caracteres"
		)
	private String documentName;

	/*
	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="typeDocument")
	private List<Document> documents;
*/
	public TypeDocument() {
	}

	public int getTypeDocumentId() {
		return this.typeDocumentId;
	}

	public void setTypeDocumentId(int typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	/*
	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setTypeDocument(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setTypeDocument(null);

		return document;
	}
	 */

}
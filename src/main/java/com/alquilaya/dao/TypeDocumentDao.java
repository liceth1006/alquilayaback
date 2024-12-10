package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.TypeDocument;
import com.alquilaya.jpa.ITypeDocumentJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class TypeDocumentDao implements ITypeDocumentDao {

	@Autowired
	ITypeDocumentJpa jpa;
	@Override
	public TypeDocument saveTypeDocument(TypeDocument typeDocument) {
		return jpa.save(typeDocument);
	}

	@Override
	public List<TypeDocument> findAllTypeDocuments() {
		return jpa.findAll();
	}

	@Override
	public Optional<TypeDocument> findTypeDocumentById(Integer id) {
		return jpa.findById(id);
	}

	@Override
	public TypeDocument updateTypeDocument(TypeDocument typeDocument, Integer id) {
		if (jpa.existsById(id)) {
			typeDocument.setTypeDocumentId(id);  
            return jpa.save(typeDocument);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

}

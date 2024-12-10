package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Document;
import com.alquilaya.jpa.IDocumentJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class DocumentDao implements IDocumentDao {

	@Autowired
	IDocumentJpa jpa;
	@Override
	public Document saveDocument(Document document) {
		return jpa.save(document);
	}

	@Override
	public List<Document> findAllDocuments() {
		return jpa.findAll();
	}

	@Override
	public Optional<Document> findDocumentById(Integer id) {
		 return jpa.findById(id);
	}

	@Override
	public Document updateDocument(Document document, Integer id) {
		if (jpa.existsById(id)) {
			document.setDocumentId(id);  
            return jpa.save(document);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

}

package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Document;

public interface IDocumentDao {
	Document saveDocument(Document document);
    List<Document> findAllDocuments();
    Optional<Document> findDocumentById(Integer id);
    Document updateDocument(Document document, Integer id);
    
}

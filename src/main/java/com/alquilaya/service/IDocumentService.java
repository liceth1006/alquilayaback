package com.alquilaya.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.alquilaya.entity.Document;

public interface IDocumentService {
	Document saveDocument(Document document);
    List<Document> findAllDocuments();
    Optional<Document> findDocumentById(Integer id);
    Document updateDocument(Document document, Integer id);
    
    void init() throws IOException;
    String store(MultipartFile file, Integer id);
    Resource loadAsResource(String filename);
}

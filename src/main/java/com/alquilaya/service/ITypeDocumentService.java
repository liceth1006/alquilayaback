package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.TypeDocument;

public interface ITypeDocumentService {
	TypeDocument saveTypeDocument(TypeDocument typeDocument);
    List<TypeDocument> findAllTypeDocuments();
    Optional<TypeDocument> findTypeDocumentById(Integer id);
    TypeDocument updateTypeDocument(TypeDocument typeDocument, Integer id);
    
}

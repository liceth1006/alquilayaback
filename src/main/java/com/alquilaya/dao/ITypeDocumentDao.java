package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.TypeDocument;

public interface ITypeDocumentDao {
	TypeDocument saveTypeDocument(TypeDocument typeDocument);
    List<TypeDocument> findAllTypeDocuments();
    Optional<TypeDocument> findTypeDocumentById(Integer id);
    TypeDocument updateTypeDocument(TypeDocument typeDocument, Integer id);
    
}

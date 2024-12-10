package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.ITypeDocumentDao;
import com.alquilaya.entity.TypeDocument;

@Service
public class TypeDocumentService implements ITypeDocumentService {

	@Autowired
	ITypeDocumentDao dao;
	@Override
	public TypeDocument saveTypeDocument(TypeDocument typeDocument) {
		
		return dao.saveTypeDocument(typeDocument);
	}

	@Override
	public List<TypeDocument> findAllTypeDocuments() {
		
		return dao.findAllTypeDocuments();
	}

	@Override
	public Optional<TypeDocument> findTypeDocumentById(Integer id) {
		
		return dao.findTypeDocumentById(id);
	}

	@Override
	public TypeDocument updateTypeDocument(TypeDocument typeDocument, Integer id) {
		
		return dao.updateTypeDocument(typeDocument, id);
	}

}

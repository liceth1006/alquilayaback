package com.alquilaya.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilaya.entity.Document;

public interface IDocumentJpa extends JpaRepository<Document, Integer>  {

}

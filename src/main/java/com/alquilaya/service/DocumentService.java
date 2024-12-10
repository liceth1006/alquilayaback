package com.alquilaya.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alquilaya.dao.IDocumentDao;
import com.alquilaya.entity.Document;
import com.alquilaya.jpa.IDocumentJpa;

import jakarta.annotation.PostConstruct;

@Service
public class DocumentService  implements IDocumentService{

	@Autowired
	IDocumentDao dao;
	
	@Autowired
	IDocumentJpa jpa;
	
	@Override
	public Document saveDocument(Document document) {
		return dao.saveDocument(document);
	}

	@Override
	public List<Document> findAllDocuments() {
		return dao.findAllDocuments();
	}

	@Override
	public Optional<Document> findDocumentById(Integer id) {
		return dao.findDocumentById(id);
	}

	@Override
	public Document updateDocument(Document document, Integer id) {
		return dao.updateDocument(document, id);
	}

	@Value("${media.location}")
	private String mediaLocation;
	
	private Path rootLocation;
	
	@Override
	@PostConstruct
	public void init() throws IOException {
		rootLocation = Paths.get(mediaLocation);
		Files.createDirectories(rootLocation);
	}

	@Override
	public String store(MultipartFile file, Integer id) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file.");
			}
			
			Document newDocument = jpa.findById(id).get();
			
			String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			
			newDocument.setDocumentUrl("http://localhost:8080/api/v1/" + filename);
			newDocument.setDocumentStatus("pending");
			dao.updateDocument(newDocument, id);
			return filename;
			
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file.", e);
		}
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource((file.toUri()));
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file: " + filename);
		}
	}

}

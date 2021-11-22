package com.legiz.terasoftproject.services.domain.service;

import com.legiz.terasoftproject.services.domain.model.entity.LawDocument;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LawDocumentService {
    List<LawDocument> getAll();
    Page<LawDocument> getAll(Pageable pageable);
    LawDocument getById(Long lawDocumentId);
    LawDocument create(LawDocument request);
    ResponseEntity<?> delete(Long lawDocumentId);
}

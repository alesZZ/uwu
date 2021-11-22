package com.legiz.terasoftproject.services.domain.service;

import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomLegalCaseService {
    List<CustomLegalCase> getAll();
    Page<CustomLegalCase> getAll(Pageable pageable);
    CustomLegalCase getById(Long customLegalCaseId);
    CustomLegalCase create(CustomLegalCase request);
    CustomLegalCase update(Long customLegalCaseId, CustomLegalCase request);
    ResponseEntity<?> delete(Long customLegalCaseId);
}

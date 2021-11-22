package com.legiz.terasoftproject.services.domain.service;

import com.legiz.terasoftproject.services.domain.model.entity.LegalAdvice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LegalAdviceService {
    List<LegalAdvice> getAll();
    Page<LegalAdvice> getAll(Pageable pageable);
    LegalAdvice getById(Long legalAdviceId);
    LegalAdvice create(LegalAdvice request);
    LegalAdvice update(Long legalAdviceId, LegalAdvice request);
    ResponseEntity<?> delete(Long legalAdviceId);
}

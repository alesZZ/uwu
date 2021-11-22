package com.legiz.terasoftproject.qualification.domain.service;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QualificationLegalAdviceService {
    List<Qualification> getAllByLegalAdviceId(Long legalAdviceId);
    Page<Qualification> getAllByLegalAdviceId(Long legalAdviceId, Pageable pageable);
    Qualification create(Long legalAdviceId, Qualification request);
    Qualification update(Long legalAdviceId, Long qualificationId, Qualification request);
    ResponseEntity<?> delete(Long legalAdviceId, Long qualificationId);
}

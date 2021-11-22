package com.legiz.terasoftproject.services.service;

import com.legiz.terasoftproject.services.domain.model.entity.LawDocument;
import com.legiz.terasoftproject.services.domain.persistence.LawDocumentRepository;
import com.legiz.terasoftproject.services.domain.service.LawDocumentService;
import com.legiz.terasoftproject.shared.exception.ResourceNotFoundException;
import com.legiz.terasoftproject.shared.exception.ResourceValidationException;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class LawDocumentServiceImpl implements LawDocumentService {

    private static final String ENTITY = "LawDocument";
    private LawDocumentRepository lawDocumentRepository;
    private final Validator validator;

    public LawDocumentServiceImpl(LawDocumentRepository lawDocumentRepository, Validator validator) {
        this.lawDocumentRepository = lawDocumentRepository;
        this.validator = validator;
    }

    @Override
    public List<LawDocument> getAll() {
        return lawDocumentRepository.findAll();
    }

    @Override
    public Page<LawDocument> getAll(Pageable pageable) {
        return lawDocumentRepository.findAll(pageable);
    }

    @Override
    public LawDocument getById(Long lawDocumentId) {
        return lawDocumentRepository.findById(lawDocumentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, lawDocumentId));
    }

    @Override
    public LawDocument create(LawDocument request) {
        Set<ConstraintViolation<LawDocument>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return lawDocumentRepository.save(request);
    }

    @Override
    public ResponseEntity<?> delete(Long lawDocumentId) {
        return lawDocumentRepository.findById(lawDocumentId).map(lawDocument -> {
            lawDocumentRepository.delete(lawDocument);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, lawDocumentId));
    }
}

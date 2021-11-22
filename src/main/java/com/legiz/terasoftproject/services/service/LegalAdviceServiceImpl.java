package com.legiz.terasoftproject.services.service;

import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import com.legiz.terasoftproject.services.domain.model.entity.LegalAdvice;
import com.legiz.terasoftproject.services.domain.persistence.CustomLegalCaseRepository;
import com.legiz.terasoftproject.services.domain.persistence.LegalAdviceRepository;
import com.legiz.terasoftproject.services.domain.service.LegalAdviceService;
import com.legiz.terasoftproject.shared.exception.ResourceNotFoundException;
import com.legiz.terasoftproject.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class LegalAdviceServiceImpl implements LegalAdviceService {

    private static final String ENTITY = "LegalAdvice";
    private LegalAdviceRepository legalAdviceRepository;
    private final Validator validator;

    public LegalAdviceServiceImpl(LegalAdviceRepository legalAdviceRepository, Validator validator) {
        this.legalAdviceRepository = legalAdviceRepository;
        this.validator = validator;
    }

    @Override
    public List<LegalAdvice> getAll() {
        return legalAdviceRepository.findAll();
    }

    @Override
    public Page<LegalAdvice> getAll(Pageable pageable) {
        return legalAdviceRepository.findAll(pageable);
    }

    @Override
    public LegalAdvice getById(Long legalAdviceId) {
        return legalAdviceRepository.findById(legalAdviceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, legalAdviceId));
    }

    @Override
    public LegalAdvice create(LegalAdvice request) {
        Set<ConstraintViolation<LegalAdvice>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return legalAdviceRepository.save(request);
    }

    @Override
    public LegalAdvice update(Long legalAdviceId, LegalAdvice request) {
        Set<ConstraintViolation<LegalAdvice>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return legalAdviceRepository.findById(legalAdviceId).map(legalAdvice -> {
            legalAdvice.setTitle(request.getTitle());
            legalAdvice.setDescription(request.getDescription());
            legalAdvice.setStatusLawService(request.getStatusLawService());
            return legalAdviceRepository.save(legalAdvice);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, legalAdviceId));
    }

    @Override
    public ResponseEntity<?> delete(Long legalAdviceId) {
        return legalAdviceRepository.findById(legalAdviceId).map(legalAdvice -> {
            legalAdviceRepository.delete(legalAdvice);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, legalAdviceId));
    }
}

package com.legiz.terasoftproject.qualification.service;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import com.legiz.terasoftproject.qualification.domain.persistence.QualificationCustomLegalCaseRepository;
import com.legiz.terasoftproject.qualification.domain.service.QualificationCustomLegalCaseService;
import com.legiz.terasoftproject.services.domain.persistence.CustomLegalCaseRepository;
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
public class QualificationCustomLegalCaseImpl implements QualificationCustomLegalCaseService {

    private static final String ENTITY = "Qualification";

    private final QualificationCustomLegalCaseRepository qualificationCustomLegalCaseRepository;
    private final CustomLegalCaseRepository customLegalCaseRepository;
    private final Validator validator;

    public QualificationCustomLegalCaseImpl(QualificationCustomLegalCaseRepository qualificationCustomLegalCaseRepository, CustomLegalCaseRepository customLegalCaseRepository, Validator validator) {
        this.qualificationCustomLegalCaseRepository = qualificationCustomLegalCaseRepository;
        this.customLegalCaseRepository = customLegalCaseRepository;
        this.validator = validator;
    }

    @Override
    public List<Qualification> getAllByCustomLegalCaseId(Long customLegalCaseId) {
        return qualificationCustomLegalCaseRepository.findByCustomLegalCaseId(customLegalCaseId);
    }

    @Override
    public Page<Qualification> getAllByCustomLegalCaseId(Long customLegalCaseId, Pageable pageable) {
        return qualificationCustomLegalCaseRepository.findByCustomLegalCaseId(customLegalCaseId, pageable);
    }

    @Override
    public Qualification create(Long customLegalCaseId, Qualification request) {
        Set<ConstraintViolation<Qualification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return customLegalCaseRepository.findById(customLegalCaseId).map(customLegalCase -> {
            request.setCustomLegalCase(customLegalCase);
            return qualificationCustomLegalCaseRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Custom Legal Case", customLegalCaseId));
    }

    @Override
    public Qualification update(Long customLegalCaseId, Long qualificationId, Qualification request) {
        Set<ConstraintViolation<Qualification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!customLegalCaseRepository.existsById(customLegalCaseId))
            throw new ResourceNotFoundException("Custom Legal Case", customLegalCaseId);

        return qualificationCustomLegalCaseRepository.findById(qualificationId).map(qualification ->
                qualificationCustomLegalCaseRepository.save(qualification
                        .withScore(request.getScore())
                        .withComment(request.getComment()))
                ).orElseThrow(() -> new ResourceNotFoundException("Custom Legal Case", customLegalCaseId));
    }

    @Override
    public ResponseEntity<?> delete(Long customLegalCaseId, Long qualificationId) {
        return qualificationCustomLegalCaseRepository.findByIdAndCustomLegalCaseId(qualificationId, customLegalCaseId).map( qualification -> {
                    qualificationCustomLegalCaseRepository.delete(qualification);
                    return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, customLegalCaseId));
    }
}

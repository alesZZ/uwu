package com.legiz.terasoftproject.qualification.service;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import com.legiz.terasoftproject.qualification.domain.persistence.QualificationLegalAdviceRepository;
import com.legiz.terasoftproject.qualification.domain.service.QualificationLegalAdviceService;
import com.legiz.terasoftproject.services.domain.persistence.LegalAdviceRepository;
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
public class QualificationLegalAdviceImpl implements QualificationLegalAdviceService {

    private static final String ENTITY = "Qualification";

    private final QualificationLegalAdviceRepository qualificationLegalAdviceRepository;
    private final LegalAdviceRepository legalAdviceRepository;
    private final Validator validator;

    public QualificationLegalAdviceImpl(QualificationLegalAdviceRepository qualificationLegalAdviceRepository, LegalAdviceRepository legalAdviceRepository, Validator validator) {
        this.qualificationLegalAdviceRepository = qualificationLegalAdviceRepository;
        this.legalAdviceRepository = legalAdviceRepository;
        this.validator = validator;
    }

    @Override
    public List<Qualification> getAllByLegalAdviceId(Long legalAdviceId) {
        return qualificationLegalAdviceRepository.findByLegalAdviceId(legalAdviceId);
    }

    @Override
    public Page<Qualification> getAllByLegalAdviceId(Long legalAdviceId, Pageable pageable) {
        return qualificationLegalAdviceRepository.findByLegalAdviceId(legalAdviceId, pageable);
    }

    @Override
    public Qualification create(Long legalAdviceId, Qualification request) {
        Set<ConstraintViolation<Qualification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return legalAdviceRepository.findById(legalAdviceId).map(legalAdvice -> {
            request.setLegalAdvice(legalAdvice);
            return qualificationLegalAdviceRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Legal Advice", legalAdviceId));
    }

    @Override
    public Qualification update(Long legalAdviceId, Long qualificationId, Qualification request) {
        Set<ConstraintViolation<Qualification>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!legalAdviceRepository.existsById(legalAdviceId))
            throw new ResourceNotFoundException("Legal Advice", legalAdviceId);

        return qualificationLegalAdviceRepository.findById(qualificationId).map(qualification ->
            qualificationLegalAdviceRepository.save(qualification
                    .withScore(request.getScore())
                    .withComment(request.getComment())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, qualificationId));
    }

    @Override
    public ResponseEntity<?> delete(Long legalAdviceId, Long qualificationId) {
        return qualificationLegalAdviceRepository.findByIdAndLegalAdviceId(qualificationId, legalAdviceId).map( qualification -> {
            qualificationLegalAdviceRepository.delete(qualification);
            return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, legalAdviceId));
    }
}

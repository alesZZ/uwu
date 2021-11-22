package com.legiz.terasoftproject.services.service;

import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import com.legiz.terasoftproject.services.domain.persistence.CustomLegalCaseRepository;
import com.legiz.terasoftproject.services.domain.service.CustomLegalCaseService;
import com.legiz.terasoftproject.shared.exception.ResourceNotFoundException;
import com.legiz.terasoftproject.shared.exception.ResourceValidationException;
import com.legiz.terasoftproject.userProfile.domain.persistence.CustomerRepository;
import com.legiz.terasoftproject.userProfile.domain.persistence.LawyerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CustomLegalCaseServiceImpl implements CustomLegalCaseService {

    private static final String ENTITY = "CustomLegalCase";
    private CustomLegalCaseRepository customLegalCaseRepository;
    private CustomerRepository customerRepository;
    private LawyerRepository lawyerRepository;
    private final Validator validator;

    public CustomLegalCaseServiceImpl(CustomLegalCaseRepository customLegalCaseRepository,CustomerRepository customerRepository, LawyerRepository lawyerRepository, Validator validator) {
        this.validator = validator;
        this.customLegalCaseRepository = customLegalCaseRepository;
        this.customerRepository = customerRepository;
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public List<CustomLegalCase> getAll() {
        return customLegalCaseRepository.findAll();
    }

    @Override
    public Page<CustomLegalCase> getAll(Pageable pageable) {
        return customLegalCaseRepository.findAll(pageable);
    }

    @Override
    public CustomLegalCase getById(Long customLegalCaseId) {
        return customLegalCaseRepository.findById(customLegalCaseId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, customLegalCaseId));
    }

    @Override
    public CustomLegalCase create(CustomLegalCase request) {
        Set<ConstraintViolation<CustomLegalCase>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Validate Customer Id
        if (!customerRepository.existsById(request.getCustomer().getId()))
            throw new ResourceNotFoundException("Customer", request.getCustomer().getId());

        // Validate Lawyer Id
        if (!lawyerRepository.existsById(request.getLawyer().getId()))
            throw new ResourceNotFoundException("Lawyer", request.getLawyer().getId());


        return customLegalCaseRepository.save(request);
    }

    @Override
    public CustomLegalCase update(Long customLegalCaseId, CustomLegalCase request) {
        Set<ConstraintViolation<CustomLegalCase>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return customLegalCaseRepository.findById(customLegalCaseId).map(customLegalCase -> {
            customLegalCase.setStartAt(request.getStartAt());
            customLegalCase.setFinishAt(request.getFinishAt());
            customLegalCase.setTypeMeet(request.getTypeMeet());
            customLegalCase.setStatusLawService(request.getStatusLawService());
            return customLegalCaseRepository.save(customLegalCase);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, customLegalCaseId));
    }

    @Override
    public ResponseEntity<?> delete(Long customLegalCaseId) {
        return customLegalCaseRepository.findById(customLegalCaseId).map(customLegalCase -> {
            customLegalCaseRepository.delete(customLegalCase);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, customLegalCaseId));
    }
}

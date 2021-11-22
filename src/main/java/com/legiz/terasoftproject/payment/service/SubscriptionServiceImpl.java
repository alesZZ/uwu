package com.legiz.terasoftproject.payment.service;

import com.legiz.terasoftproject.payment.domain.model.entity.Subscription;
import com.legiz.terasoftproject.payment.domain.persistence.SubscriptionRepository;
import com.legiz.terasoftproject.payment.domain.service.SubscriptionService;
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
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final String ENTITY = "Subscription";
    private SubscriptionRepository subscriptionRepository;
    private final Validator validator;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, Validator validator) {
        this.subscriptionRepository = subscriptionRepository;
        this.validator = validator;
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Page<Subscription> getAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Override
    public Subscription getById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }

    @Override
    public Subscription create(Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Subscription subscriptionWithDescription = subscriptionRepository.findByDescription(request.getDescription());

        if (subscriptionWithDescription != null)
            throw new ResourceValidationException(ENTITY, "A Subscription with the same description already exists.");

        return subscriptionRepository.save(request);
    }

    @Override
    public Subscription update(Long subscriptionId, Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Subscription subscriptionWithDescription = subscriptionRepository.findByDescription(request.getDescription());

        if (subscriptionWithDescription != null)
            throw new ResourceValidationException(ENTITY, "A Subscription with the same description already exists.");

        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            subscription.setPrice(request.getPrice());
            subscription.setDescription(request.getDescription());
            return subscriptionRepository.save(subscription);
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, subscriptionId));
    }

    @Override
    public ResponseEntity<?> delete(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            subscriptionRepository.delete(subscription);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, subscriptionId));
    }
}

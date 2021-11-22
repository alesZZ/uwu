package com.legiz.terasoftproject.payment.domain.service;

import com.legiz.terasoftproject.payment.domain.model.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Page<Subscription> getAll(Pageable pageable);
    Subscription getById(Long subscriptionId);
    Subscription create(Subscription request);
    Subscription update(Long subscriptionId, Subscription request);
    ResponseEntity<?> delete(Long subscriptionId);
}

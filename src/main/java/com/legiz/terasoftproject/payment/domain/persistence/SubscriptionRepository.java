package com.legiz.terasoftproject.payment.domain.persistence;

import com.legiz.terasoftproject.payment.domain.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByDescription(String description);
}

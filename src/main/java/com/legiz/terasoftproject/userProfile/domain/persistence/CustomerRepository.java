package com.legiz.terasoftproject.userProfile.domain.persistence;

import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}

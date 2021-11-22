package com.legiz.terasoftproject.userProfile.domain.service;

import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterCustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Page<Customer> getAll(Pageable pageable);
    Customer getById(Long customerId);
    ResponseEntity<?> register(RegisterCustomerRequest request);
    Customer update(Long customerId, Customer request);
    ResponseEntity<?> delete(Long customerId);
}

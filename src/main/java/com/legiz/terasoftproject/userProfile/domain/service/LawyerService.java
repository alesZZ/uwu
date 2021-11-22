package com.legiz.terasoftproject.userProfile.domain.service;

import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterLawyerRequest;
import com.legiz.terasoftproject.userProfile.resource.UpdateLawyerResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LawyerService {
    List<Lawyer> getAll();
    Page<Lawyer> getAll(Pageable pageable);
    Lawyer getById(Long lawyerId);
    ResponseEntity<?> register(RegisterLawyerRequest request);
    ResponseEntity<?> update(Long lawyerId, UpdateLawyerResource request);
    ResponseEntity<?> delete(Long lawyerId);
}

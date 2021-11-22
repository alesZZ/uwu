package com.legiz.terasoftproject.security.domain.service;

import com.legiz.terasoftproject.security.domain.model.entity.User;
import com.legiz.terasoftproject.security.domain.service.communication.AuthenticateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    List<User> getAll();
}

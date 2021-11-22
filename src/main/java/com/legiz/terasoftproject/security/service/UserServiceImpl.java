package com.legiz.terasoftproject.security.service;

import com.legiz.terasoftproject.security.domain.model.entity.User;
import com.legiz.terasoftproject.security.domain.persistence.RoleRepository;
import com.legiz.terasoftproject.security.domain.persistence.UserRepository;
import com.legiz.terasoftproject.security.domain.service.UserService;
import com.legiz.terasoftproject.security.domain.service.communication.AuthenticateRequest;
import com.legiz.terasoftproject.security.domain.service.communication.AuthenticateResponse;
import com.legiz.terasoftproject.security.middleware.JwtHandler;
import com.legiz.terasoftproject.security.middleware.UserDetailsImpl;
import com.legiz.terasoftproject.security.resource.AuthenticateResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandler handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = handler.generateToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateResponse response = new AuthenticateResponse(resource);

            return ResponseEntity.ok(response);

        } catch(Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(
                    String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}

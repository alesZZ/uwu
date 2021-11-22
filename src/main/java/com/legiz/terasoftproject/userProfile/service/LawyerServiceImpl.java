package com.legiz.terasoftproject.userProfile.service;

import com.legiz.terasoftproject.payment.domain.persistence.SubscriptionRepository;
import com.legiz.terasoftproject.security.domain.model.entity.Role;
import com.legiz.terasoftproject.security.domain.model.enumeration.Roles;
import com.legiz.terasoftproject.security.domain.persistence.RoleRepository;
import com.legiz.terasoftproject.security.domain.persistence.UserRepository;
import com.legiz.terasoftproject.security.domain.service.communication.AuthenticateResponse;
import com.legiz.terasoftproject.security.middleware.JwtHandler;
import com.legiz.terasoftproject.shared.exception.ResourceNotFoundException;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import com.legiz.terasoftproject.userProfile.domain.persistence.LawyerRepository;
import com.legiz.terasoftproject.userProfile.domain.service.LawyerService;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterCustomerResponse;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterLawyerRequest;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterLawyerResponse;
import com.legiz.terasoftproject.userProfile.resource.LawyerSubscriptionResource;
import com.legiz.terasoftproject.userProfile.resource.UpdateLawyerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LawyerServiceImpl implements LawyerService {

    private static final Logger logger = LoggerFactory.getLogger(LawyerServiceImpl.class);
    private static final String ENTITY = "Lawyer";
    private LawyerRepository lawyerRepository;
    private SubscriptionRepository subscriptionRepository;
    private final Validator validator;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtHandler jwtHandler;
    private EnhancedModelMapper mapper;

    public LawyerServiceImpl(LawyerRepository lawyerRepository, SubscriptionRepository subscriptionRepository, Validator validator, UserRepository userRepository,
                             RoleRepository roleRepository, PasswordEncoder encoder, JwtHandler jwtHandler, EnhancedModelMapper mapper) {
        this.lawyerRepository = lawyerRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.validator = validator;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtHandler = jwtHandler;
        this.mapper = mapper;
    }

    @Override
    public List<Lawyer> getAll() {
        return lawyerRepository.findAll();
    }

    @Override
    public Page<Lawyer> getAll(Pageable pageable) {
        return lawyerRepository.findAll(pageable);
    }

    @Override
    public Lawyer getById(Long lawyerId) {
        return lawyerRepository.findById(lawyerId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY, lawyerId));
    }

    @Override
    public ResponseEntity<?> register(RegisterLawyerRequest request) {

        // Validate User with != Username
        if(userRepository.existsByUsername(request.getUsername())) {
            AuthenticateResponse response = new AuthenticateResponse("Username is already taken.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        // Validate User with != Email
        if(userRepository.existsByEmail(request.getEmail())) {
            AuthenticateResponse response = new AuthenticateResponse("Email is already taken.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_LAWYER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            Lawyer alternativeLaw = mapper.map(request, Lawyer.class);

            logger.info("Roles: {}", roles);

            Lawyer lawyer = new Lawyer(
                    request.getUsername(),
                    encoder.encode(request.getPassword()),
                    request.getEmail(),
                    roles,
                    request.getLawyerName(),
                    request.getLawyerLastName(),
                    alternativeLaw.getSpecialization(),
                    request.getPriceLegalAdvice(),
                    request.getPriceCustomLegalCase(),
                    alternativeLaw.getSubscription());

            lawyerRepository.save(lawyer);

            LawyerSubscriptionResource resource = mapper.map(lawyer, LawyerSubscriptionResource.class);
            RegisterLawyerResponse response = new RegisterLawyerResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            RegisterCustomerResponse response = new RegisterCustomerResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> update(Long lawyerId, UpdateLawyerResource request) {

            return lawyerRepository.findById(lawyerId).map(lawyer -> {
                lawyer.setUsername(request.getUsername());
                lawyer.setEmail(request.getEmail());
                lawyer.setPassword(encoder.encode(request.getPassword()));
                lawyer.setLawyerName(request.getLawyerName());
                lawyer.setLawyerLastName(request.getLawyerLastName());
                lawyer.setPriceLegalAdvice(request.getPriceLegalAdvice());
                lawyer.setPriceCustomLegalCase(request.getPriceCustomLegalCase());
                lawyerRepository.save(lawyer);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, lawyerId));

    }

    @Override
    public ResponseEntity<?> delete(Long lawyerId) {
        return lawyerRepository.findById(lawyerId).map(lawyer -> {
            lawyerRepository.delete(lawyer);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, lawyerId));
    }
}

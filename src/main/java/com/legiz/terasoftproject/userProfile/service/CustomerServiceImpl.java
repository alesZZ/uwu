package com.legiz.terasoftproject.userProfile.service;

import com.legiz.terasoftproject.security.domain.model.entity.Role;
import com.legiz.terasoftproject.security.domain.model.enumeration.Roles;
import com.legiz.terasoftproject.security.domain.persistence.RoleRepository;
import com.legiz.terasoftproject.security.domain.persistence.UserRepository;
import com.legiz.terasoftproject.security.domain.service.communication.AuthenticateResponse;
import com.legiz.terasoftproject.security.middleware.JwtHandler;
import com.legiz.terasoftproject.security.service.UserServiceImpl;
import com.legiz.terasoftproject.shared.exception.ResourceNotFoundException;
import com.legiz.terasoftproject.shared.exception.ResourceValidationException;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import com.legiz.terasoftproject.userProfile.domain.persistence.CustomerRepository;
import com.legiz.terasoftproject.userProfile.domain.service.CustomerService;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterCustomerRequest;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterCustomerResponse;
import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private static final String ENTITY = "Customer";
    private CustomerRepository customerRepository;
    private final Validator validator;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtHandler jwtHandler;
    private EnhancedModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Validator validator, UserRepository userRepository, RoleRepository roleRepository,
                               PasswordEncoder encoder, JwtHandler jwtHandler, EnhancedModelMapper mapper) {
        this.validator = validator;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtHandler = jwtHandler;
        this.mapper = mapper;
    }


    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }

    @Override
    public ResponseEntity<?> register(RegisterCustomerRequest request) {

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

            if(rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_CUSTOMER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            Customer customer = new Customer(
                    request.getUsername(),
                    encoder.encode(request.getPassword()),
                    request.getEmail(),
                    roles,
                    request.getCustomerName(),
                    request.getCustomerLastName());

            customerRepository.save(customer);
            CustomerResource resource = mapper.map(customer, CustomerResource.class);
            RegisterCustomerResponse response = new RegisterCustomerResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            RegisterCustomerResponse response = new RegisterCustomerResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public Customer update(Long customerId, Customer request) {

        return customerRepository.findById(customerId).map(customer -> {
            customer.setEmail(request.getEmail());
            customer.setUsername(request.getUsername());
            customer.setPassword(encoder.encode(request.getPassword()));
            customer.setCustomerName(request.getCustomerName());
            customer.setCustomerLastName(request.getCustomerLastName());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }

    @Override
    public ResponseEntity<?> delete(Long customerId) {
        return customerRepository.findById(customerId).map(lawyer -> {
            customerRepository.delete(lawyer);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, customerId));
    }
}

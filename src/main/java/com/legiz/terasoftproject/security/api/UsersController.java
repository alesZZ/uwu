package com.legiz.terasoftproject.security.api;

import com.legiz.terasoftproject.security.domain.service.UserService;
import com.legiz.terasoftproject.security.domain.service.communication.AuthenticateRequest;
import com.legiz.terasoftproject.security.mapping.UserMapper;
import com.legiz.terasoftproject.security.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Users")
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Authenticate User", description = "Authenticate User already exist")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticated"
            )
    })
    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    @Operation(summary = "Get Users", description = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found"
            )
    })
    @GetMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        Page<UserResource> resources = mapper.modelListToPage(userService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }
}

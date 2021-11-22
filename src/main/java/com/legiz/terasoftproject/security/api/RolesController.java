package com.legiz.terasoftproject.security.api;

import com.legiz.terasoftproject.security.domain.service.RoleService;
import com.legiz.terasoftproject.security.mapping.RoleMapper;
import com.legiz.terasoftproject.security.resource.RoleResource;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Roles")
@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

    private final RoleService roleService;
    private final RoleMapper mapper;


    public RolesController(RoleService roleService, RoleMapper mapper) {
        this.roleService =roleService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Roles", description = "Get All Roles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Role found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RoleResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> getAllRoles(Pageable pageable) {
        Page<RoleResource> resources = mapper.modelListToPage(roleService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }

}

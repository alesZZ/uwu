package com.legiz.terasoftproject.services.api;

import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import com.legiz.terasoftproject.services.domain.service.CustomLegalCaseService;
import com.legiz.terasoftproject.services.mapping.CustomLegalCaseMapper;
import com.legiz.terasoftproject.services.resource.CreateCustomLegalCaseResource;
import com.legiz.terasoftproject.services.resource.CustomLegalCaseResource;
import com.legiz.terasoftproject.services.resource.UpdateCustomLegalCaseResource;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "CustomLegalCases")
@RestController
@RequestMapping("/api/v1/customlegalcases")
public class CustomLegalCaseController {

    private final CustomLegalCaseService customLegalCaseService;
    private final CustomLegalCaseMapper mapper;

    public CustomLegalCaseController(CustomLegalCaseService customLegalCaseService, CustomLegalCaseMapper mapper) {
        this.customLegalCaseService = customLegalCaseService;
        this.mapper = mapper;
    }


    @Operation(summary = "Get Custom Legal Cases", description = "Get All Custom Legal Cases")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Custom Legal Case found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CustomLegalCaseResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public Page<CustomLegalCaseResource> getAllCustomLegalCase(Pageable pageable) {
        return mapper.modelListToPage(customLegalCaseService.getAll(), pageable);
    }

    @Operation(summary = "Get Custom Legal Case By Id", description = "Get Custom Legal Case already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Custom Legal Case found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CustomLegalCaseResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{customlegalcaseId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public CustomLegalCaseResource getCustomLegalCaseById(@PathVariable Long customlegalcaseId) {
        return mapper.toResource(customLegalCaseService.getById(customlegalcaseId));
    }

    @Operation(summary = "Create Custom Legal Case", description = "Create a new Custom Legal Case")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Custom Legal Case created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateCustomLegalCaseResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public CustomLegalCaseResource createCustomLegalCase(@RequestBody CreateCustomLegalCaseResource request) {
        return mapper.toResource(customLegalCaseService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update Custom Legal Case", description = "Update Custom Legal Case already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Custom Legal Case updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateCustomLegalCaseResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{customlegalcaseId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public CustomLegalCaseResource updateCustomLegalCase(@PathVariable Long customlegalcaseId, @RequestBody UpdateCustomLegalCaseResource request) {
        return mapper.toResource(customLegalCaseService.update(customlegalcaseId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Custom Legal Case", description = "Delete Custom Legal Case already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Custom Legal Case deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @DeleteMapping("{customlegalcaseId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteCustomLegalCase(@PathVariable Long customlegalcaseId) {
        return customLegalCaseService.delete(customlegalcaseId);
    }
}

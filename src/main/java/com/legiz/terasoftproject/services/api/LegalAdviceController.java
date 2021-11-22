package com.legiz.terasoftproject.services.api;

import com.legiz.terasoftproject.services.domain.model.entity.LegalAdvice;
import com.legiz.terasoftproject.services.domain.service.LegalAdviceService;
import com.legiz.terasoftproject.services.mapping.LegalAdviceMapper;
import com.legiz.terasoftproject.services.resource.CreateLegalAdviceResource;
import com.legiz.terasoftproject.services.resource.LegalAdviceResource;
import com.legiz.terasoftproject.services.resource.UpdateLegalAdviceResource;
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
@Tag(name = "LegalAdvices")
@RestController
@RequestMapping("/api/v1/legaladvices")
public class LegalAdviceController {

    private final LegalAdviceService legalAdviceService;
    private final LegalAdviceMapper mapper;

    public LegalAdviceController(LegalAdviceService legalAdviceService, LegalAdviceMapper mapper) {
        this.legalAdviceService = legalAdviceService;
        this.mapper = mapper;
    }


    @Operation(summary = "Get Legal Advices", description = "Get All Legal Advices")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Legal Advice found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LegalAdviceResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public Page<LegalAdviceResource> getAllLegalAdvice(Pageable pageable) {
        return mapper.modelListToPage(legalAdviceService.getAll(), pageable);
    }

    @Operation(summary = "Get Legal Advice By Id", description = "Get Legal Advice already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Legal Advice found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LegalAdviceResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{legaladviceId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public LegalAdviceResource getLegalAdviceById(@PathVariable Long legaladviceId) {
        return mapper.toResource(legalAdviceService.getById(legaladviceId));
    }

    @Operation(summary = "Create Legal Advice", description = "Create a new Legal Advice")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Legal Advice created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateLegalAdviceResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public LegalAdviceResource createLegalAdvice(@RequestBody CreateLegalAdviceResource request) {
        return mapper.toResource(legalAdviceService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update Legal Advice", description = "Update Legal Advice already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Legal Advice updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LegalAdviceResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{legaladviceId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public LegalAdviceResource updateLegalAdvice(@PathVariable Long legaladviceId, @RequestBody UpdateLegalAdviceResource request) {
        return mapper.toResource(legalAdviceService.update(legaladviceId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Legal Advice", description = "Delete Legal Advice already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Legal Advice deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @DeleteMapping("{legaladviceId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteLegalAdvice(@PathVariable Long legaladviceId) {
        return legalAdviceService.delete(legaladviceId);
    }
}

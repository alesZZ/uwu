package com.legiz.terasoftproject.services.api;

import com.legiz.terasoftproject.services.domain.service.LawDocumentService;
import com.legiz.terasoftproject.services.mapping.LawDocumentMapper;
import com.legiz.terasoftproject.services.resource.CreateLawDocumentResource;
import com.legiz.terasoftproject.services.resource.LawDocumentResource;
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
@Tag(name = "Documents")
@RestController
@RequestMapping("/api/v1/documents")
public class LawDocumentController {

    private final LawDocumentService lawDocumentService;
    private final LawDocumentMapper mapper;

    public LawDocumentController(LawDocumentService lawDocumentService, LawDocumentMapper mapper) {
        this.lawDocumentService = lawDocumentService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Documents", description = "Get All Documents")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LawDocumentResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public Page<LawDocumentResource> getAllLawDocument(Pageable pageable) {
        return mapper.modelListToPage(lawDocumentService.getAll(), pageable);
    }

    @Operation(summary = "Get Document By Id", description = "Get Document already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LawDocumentResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{documentId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public LawDocumentResource getLawDocumentById(@PathVariable Long documentId) {
        return mapper.toResource(lawDocumentService.getById(documentId));
    }

    @Operation(summary = "Create Document", description = "Create a new Document")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateLawDocumentResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public LawDocumentResource createLawDocument(@RequestBody CreateLawDocumentResource request) {
        return mapper.toResource(lawDocumentService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Delete Document", description = "Delete Document already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @DeleteMapping("{documentId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteDocument(@PathVariable Long documentId) {
        return lawDocumentService.delete(documentId);
    }
}

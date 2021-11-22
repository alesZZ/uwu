package com.legiz.terasoftproject.qualification.api;

import com.legiz.terasoftproject.qualification.domain.service.QualificationLegalAdviceService;
import com.legiz.terasoftproject.qualification.mapping.QualificationMapper;
import com.legiz.terasoftproject.qualification.resource.CreateQualificationResource;
import com.legiz.terasoftproject.qualification.resource.QualificationResource;
import com.legiz.terasoftproject.qualification.resource.UpdateQualificationResource;
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
@Tag(name = "Qualifications")
@RestController
@RequestMapping("api/v1/legaladvices/{legaladviceId}/qualifications")
public class QualificationLegalAdviceController {

    private final QualificationLegalAdviceService qualificationLegalAdviceService;
    private final QualificationMapper mapper;

    public QualificationLegalAdviceController(QualificationLegalAdviceService qualificationLegalAdviceService, QualificationMapper mapper) {
        this.qualificationLegalAdviceService = qualificationLegalAdviceService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Qualifications", description = "Get All Qualifications")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Qualifications found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = QualificationResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public Page<QualificationResource> getQualificationByLegalAdviceId(@PathVariable Long legaladviceId, Pageable pageable) {
        return mapper.modelListToPage(qualificationLegalAdviceService.getAllByLegalAdviceId(legaladviceId), pageable);
    }

    @Operation(summary = "Create Qualification", description = "Create a new Qualification")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Qualification created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateQualificationResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public QualificationResource createQualification(@PathVariable Long legaladviceId, @RequestBody CreateQualificationResource request) {
        return mapper.toResource(qualificationLegalAdviceService.create(legaladviceId, mapper.toModel(request)));
    }

    @Operation(summary = "Update Qualification", description = "Update Qualification already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Qualification updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateQualificationResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{qualificationId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public QualificationResource updateQualification(@PathVariable Long legaladviceId, @PathVariable Long qualificationId, @RequestBody UpdateQualificationResource request) {
        return mapper.toResource(qualificationLegalAdviceService.update(legaladviceId, qualificationId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Qualification", description = "Delete Qualification already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Qualification deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @DeleteMapping("{qualificationId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteQualification(@PathVariable Long legaladviceId, @PathVariable Long qualificationId) {
        return qualificationLegalAdviceService.delete(legaladviceId, qualificationId);
    }
}

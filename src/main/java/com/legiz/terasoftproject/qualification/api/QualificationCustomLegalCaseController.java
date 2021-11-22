package com.legiz.terasoftproject.qualification.api;

import com.legiz.terasoftproject.payment.resource.CreateSubscriptionResource;
import com.legiz.terasoftproject.payment.resource.SubscriptionResource;
import com.legiz.terasoftproject.payment.resource.UpdateSubscriptionResource;
import com.legiz.terasoftproject.qualification.domain.service.QualificationCustomLegalCaseService;
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
@RequestMapping("api/v1/customlegalcases/{customlegalcaseId}/qualifications")
public class QualificationCustomLegalCaseController {
    private final QualificationCustomLegalCaseService qualificationCustomLegalCaseService;
    private final QualificationMapper mapper;

    public QualificationCustomLegalCaseController(QualificationCustomLegalCaseService qualificationCustomLegalCaseService, QualificationMapper mapper) {
        this.qualificationCustomLegalCaseService = qualificationCustomLegalCaseService;
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
    public Page<QualificationResource> getQualificationByCustomLegalCaseId(@PathVariable Long customlegalcaseId, Pageable pageable) {
        return mapper.modelListToPage(qualificationCustomLegalCaseService.getAllByCustomLegalCaseId(customlegalcaseId), pageable);
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
    public QualificationResource createQualification(@PathVariable Long customlegalcaseId, @RequestBody CreateQualificationResource request) {
        return mapper.toResource(qualificationCustomLegalCaseService.create(customlegalcaseId, mapper.toModel(request)));
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
    public QualificationResource updateQualification(@PathVariable Long customlegalcaseId, @PathVariable Long qualificationId, @RequestBody UpdateQualificationResource request) {
        return mapper.toResource(qualificationCustomLegalCaseService.update(customlegalcaseId, qualificationId, mapper.toModel(request)));
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
    public ResponseEntity<?> deleteQualification(@PathVariable Long customlegalcaseId, @PathVariable Long qualificationId) {
        return qualificationCustomLegalCaseService.delete(customlegalcaseId, qualificationId);
    }
}

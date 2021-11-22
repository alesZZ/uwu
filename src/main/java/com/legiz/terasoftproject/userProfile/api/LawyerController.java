package com.legiz.terasoftproject.userProfile.api;

import com.legiz.terasoftproject.userProfile.domain.service.LawyerService;
import com.legiz.terasoftproject.userProfile.domain.service.communication.RegisterLawyerRequest;
import com.legiz.terasoftproject.userProfile.mapping.LawyerMapper;
import com.legiz.terasoftproject.userProfile.resource.LawyerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerSubscriptionResource;
import com.legiz.terasoftproject.userProfile.resource.UpdateLawyerResource;
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
@Tag(name = "Lawyers")
@RestController
@RequestMapping("/api/v1/lawyers")
public class LawyerController {

    private final LawyerService lawyerService;
    private final LawyerMapper mapper;

    public LawyerController(LawyerService lawyerService, LawyerMapper mapper) {
        this.lawyerService = lawyerService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Lawyers", description = "Get All Lawyers")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lawyer found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LawyerResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public Page<LawyerSubscriptionResource> getAllLawyer(Pageable pageable) {
        return mapper.modelListToPage(lawyerService.getAll(), pageable);
    }

    @Operation(summary = "Get Lawyer By Id", description = "Get Lawyer already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lawyer found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LawyerResource.class
                                    )
                            )
                    }
            )
    })
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    @GetMapping("{lawyerId}")
    public LawyerSubscriptionResource getLawyerById(@PathVariable Long lawyerId) {
        return mapper.toResource(lawyerService.getById(lawyerId));
    }

    @Operation(summary = "Create Lawyer", description = "Create a new Lawyer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lawyer created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = RegisterLawyerRequest.class
                                    )
                            )
                    }
            )
    })
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerLawyer(@RequestBody RegisterLawyerRequest request) {
        return lawyerService.register(request);
    }

    @Operation(summary = "Update Lawyer", description = "Update Lawyer already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lawyer updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateLawyerResource.class
                                    )
                            )
                    }
            )
    })
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    @PutMapping("{lawyerId}")
    public ResponseEntity<?> updateLawyer(@PathVariable Long lawyerId, @RequestBody UpdateLawyerResource request) {
        return lawyerService.update(lawyerId,request);
    }

    @Operation(summary = "Delete Lawyer", description = "Delete Lawyer already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lawyer deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    @DeleteMapping("{lawyerId}")
    public ResponseEntity<?> deleteLawyer(@PathVariable Long lawyerId) {
        return lawyerService.delete(lawyerId);
    }
}

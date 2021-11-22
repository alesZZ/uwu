package com.legiz.terasoftproject.payment.api;

import com.legiz.terasoftproject.payment.domain.service.SubscriptionService;
import com.legiz.terasoftproject.payment.mapping.SubscriptionMapper;
import com.legiz.terasoftproject.payment.resource.CreateSubscriptionResource;
import com.legiz.terasoftproject.payment.resource.SubscriptionResource;
import com.legiz.terasoftproject.payment.resource.UpdateSubscriptionResource;
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
@Tag(name = "Subscriptions")
@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper mapper;

    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper mapper) {
        this.subscriptionService = subscriptionService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get Subscriptions", description = "Get All Subscriptions")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Subscription found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SubscriptionResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public Page<SubscriptionResource> getAllSubscription(Pageable pageable) {
        return mapper.modelListToPage(subscriptionService.getAll(), pageable);
    }

    @Operation(summary = "Get Subscription By Id", description = "Get Subscription already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Subscription found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SubscriptionResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{subscriptionId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public SubscriptionResource getSubscriptionById(@PathVariable Long subscriptionId) {
        return mapper.toResource(subscriptionService.getById(subscriptionId));
    }

    @Operation(summary = "Create Subscription", description = "Create a new Subscription")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Subscription created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateSubscriptionResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public SubscriptionResource createSubscription(@RequestBody CreateSubscriptionResource request) {
        return mapper.toResource(subscriptionService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update Subscription", description = "Update Subscription already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Subscription updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateSubscriptionResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{subscriptionId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public SubscriptionResource updateSubscription(@PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionResource request) {
        return mapper.toResource(subscriptionService.update(subscriptionId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Subscription", description = "Delete Subscription already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Subscription deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @DeleteMapping("{subscriptionId}")
    @PreAuthorize("hasRole('LAWYER') or hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId) {
        return subscriptionService.delete(subscriptionId);
    }

}

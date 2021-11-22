package com.legiz.terasoftproject.services.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateCustomLegalCaseResource {

    private String statusLawService;

    @NotBlank
    @NotNull
    private String title;

    @NotNull
    @NotBlank
    private String startAt;

    @NotNull
    @NotBlank
    private String finishAt;

    private String typeMeet;

    @NotNull
    private int lawyerId;

    @NotNull
    private int customerId;

    @NotNull
    private Long lawDocumentId;
}

package com.legiz.terasoftproject.services.resource;

import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateLegalAdviceResource {

    private String statusLawService;

    @NotBlank
    @NotNull
    private String title;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @NotNull
    private Long lawyerId;

    @NotNull
    private Long customerId;

    @NotNull
    private Long lawDocumentId;
}

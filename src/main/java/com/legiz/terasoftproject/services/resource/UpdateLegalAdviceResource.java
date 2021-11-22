package com.legiz.terasoftproject.services.resource;

import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdateLegalAdviceResource {

    private String statusLawService;

    private String title;

    private String description;
}

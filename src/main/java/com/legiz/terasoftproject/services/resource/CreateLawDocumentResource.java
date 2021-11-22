package com.legiz.terasoftproject.services.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateLawDocumentResource {
    @NotNull
    @NotBlank
    private String title;
}

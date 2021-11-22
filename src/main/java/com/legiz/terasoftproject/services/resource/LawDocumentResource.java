package com.legiz.terasoftproject.services.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class LawDocumentResource {
    private Long id;
    private String title;
    private String path;
}

package com.legiz.terasoftproject.services.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdateCustomLegalCaseResource {

    private String statusLawService;

    private String title;

    private String startAt;

    private String finishAt;


    private String typeMeet;
}

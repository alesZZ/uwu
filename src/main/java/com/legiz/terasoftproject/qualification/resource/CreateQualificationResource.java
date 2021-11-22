package com.legiz.terasoftproject.qualification.resource;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateQualificationResource {

    @Range(min = 1, max = 5)
    private Long score;

    @NotNull
    @NotBlank
    private String comment;
}

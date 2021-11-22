package com.legiz.terasoftproject.qualification.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class QualificationResource {
    private Long id;
    private Long score;
    private String comment;
}

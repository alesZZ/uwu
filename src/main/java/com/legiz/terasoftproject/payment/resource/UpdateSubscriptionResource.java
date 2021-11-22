package com.legiz.terasoftproject.payment.resource;

import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdateSubscriptionResource {

    @NotNull
    private Long price;

    @Lob
    private String description;
}

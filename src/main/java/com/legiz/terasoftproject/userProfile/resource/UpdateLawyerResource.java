package com.legiz.terasoftproject.userProfile.resource;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdateLawyerResource {

    private String username;
    private String password;
    private String email;

    private String lawyerName;

    private String lawyerLastName;

    private Long priceLegalAdvice;

    private Long priceCustomLegalCase;

}

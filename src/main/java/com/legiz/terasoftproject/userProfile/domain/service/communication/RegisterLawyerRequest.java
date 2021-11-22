package com.legiz.terasoftproject.userProfile.domain.service.communication;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class RegisterLawyerRequest {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String lawyerName;

    @NotNull
    @NotBlank
    private String lawyerLastName;

    @NotBlank
    @NotNull
    private String specialization;

    private Long priceLegalAdvice;

    private Long priceCustomLegalCase;

    @NotNull
    private int subscriptionId;

    private Set<String> roles;

}

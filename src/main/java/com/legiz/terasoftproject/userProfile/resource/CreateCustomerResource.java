package com.legiz.terasoftproject.userProfile.resource;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CreateCustomerResource {

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
    private String customerName;

    @NotNull
    @NotBlank
    private String customerLastName;
}

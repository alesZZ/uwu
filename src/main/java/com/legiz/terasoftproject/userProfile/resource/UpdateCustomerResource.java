package com.legiz.terasoftproject.userProfile.resource;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdateCustomerResource {

    private String username;

    private String password;

    private String email;

    private String customerName;

    private String customerLastName;
}

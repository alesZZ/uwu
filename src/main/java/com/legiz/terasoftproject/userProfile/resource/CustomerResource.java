package com.legiz.terasoftproject.userProfile.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CustomerResource {
    private Long id;
    private String username;
    private String email;
    private String customerName;
    private String customerLastName;
    private List<String> roles;
}

package com.legiz.terasoftproject.security.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserResource {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}

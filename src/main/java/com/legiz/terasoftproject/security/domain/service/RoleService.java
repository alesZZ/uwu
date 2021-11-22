package com.legiz.terasoftproject.security.domain.service;

import com.legiz.terasoftproject.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();
    List<Role> getAll();
}

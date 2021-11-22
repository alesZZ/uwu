package com.legiz.terasoftproject.security.mapping;

import com.legiz.terasoftproject.security.domain.model.entity.Role;
import com.legiz.terasoftproject.security.domain.model.enumeration.Roles;
import com.legiz.terasoftproject.security.resource.RoleResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RoleMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Roles, String> rolesToString = new AbstractConverter<>() {
        @Override
        protected String convert(Roles role) {
            return role == null ? null : role.name();
        }
    };

    // Object Mapping
    public RoleResource toResource(Role model) {
        mapper.addConverter(rolesToString);
        return mapper.map(model, RoleResource.class);
    }

    public Page<RoleResource> modelListToPage(List<Role> modelList, Pageable pageable) {
        mapper.addConverter(rolesToString);
        return new PageImpl<>(mapper.mapList(modelList, RoleResource.class), pageable, modelList.size());
    }
}


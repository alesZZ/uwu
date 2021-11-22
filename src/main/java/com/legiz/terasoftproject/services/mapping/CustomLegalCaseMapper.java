package com.legiz.terasoftproject.services.mapping;

import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import com.legiz.terasoftproject.services.resource.CreateCustomLegalCaseResource;
import com.legiz.terasoftproject.services.resource.CustomLegalCaseResource;
import com.legiz.terasoftproject.services.resource.UpdateCustomLegalCaseResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CustomLegalCaseMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public CustomLegalCaseResource toResource(CustomLegalCase model) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(model, CustomLegalCaseResource.class); }

    public Page<CustomLegalCaseResource> modelListToPage(List<CustomLegalCase> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CustomLegalCaseResource.class),
                pageable,
                modelList.size());
    }

    public CustomLegalCase toModel(CreateCustomLegalCaseResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, CustomLegalCase.class);
    }

    public CustomLegalCase toModel(UpdateCustomLegalCaseResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, CustomLegalCase.class); }
}

package com.legiz.terasoftproject.qualification.mapping;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import com.legiz.terasoftproject.qualification.resource.CreateQualificationResource;
import com.legiz.terasoftproject.qualification.resource.QualificationResource;
import com.legiz.terasoftproject.qualification.resource.UpdateQualificationResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class QualificationMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public QualificationResource toResource(Qualification model) {
        return mapper.map(model, QualificationResource.class);
    }

    public Page<QualificationResource> modelListToPage(List<Qualification> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, QualificationResource.class), pageable, modelList.size());
    }

    public Qualification toModel(CreateQualificationResource resource) {
        return mapper.map(resource, Qualification.class);
    }

    public Qualification toModel(UpdateQualificationResource resource) {
        return mapper.map(resource, Qualification.class);
    }
}

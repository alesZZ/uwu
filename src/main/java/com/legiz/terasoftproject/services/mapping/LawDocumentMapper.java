package com.legiz.terasoftproject.services.mapping;

import com.legiz.terasoftproject.services.domain.model.entity.LawDocument;
import com.legiz.terasoftproject.services.resource.CreateLawDocumentResource;
import com.legiz.terasoftproject.services.resource.LawDocumentResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LawDocumentMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public LawDocumentResource toResource(LawDocument model) { return mapper.map(model, LawDocumentResource.class); }

    public Page<LawDocumentResource> modelListToPage(List<LawDocument> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, LawDocumentResource.class),
                pageable,
                modelList.size());
    }

    public LawDocument toModel(CreateLawDocumentResource resource) {
        return mapper.map(resource, LawDocument.class);
    }

}

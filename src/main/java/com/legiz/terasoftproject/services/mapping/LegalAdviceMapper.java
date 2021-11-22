package com.legiz.terasoftproject.services.mapping;

import com.legiz.terasoftproject.services.domain.model.entity.LegalAdvice;
import com.legiz.terasoftproject.services.resource.CreateLegalAdviceResource;
import com.legiz.terasoftproject.services.resource.LegalAdviceResource;
import com.legiz.terasoftproject.services.resource.UpdateLegalAdviceResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LegalAdviceMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;


    public LegalAdviceResource toResource(LegalAdvice model) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(model, LegalAdviceResource.class); }

    public Page<LegalAdviceResource> modelListToPage(List<LegalAdvice> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, LegalAdviceResource.class),
                pageable,
                modelList.size());
    }

    public LegalAdvice toModel(CreateLegalAdviceResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, LegalAdvice.class);
    }

    public LegalAdvice toModel(UpdateLegalAdviceResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, LegalAdvice.class); }
}

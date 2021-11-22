package com.legiz.terasoftproject.userProfile.mapping;

import com.legiz.terasoftproject.security.domain.model.entity.Role;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerSubscriptionResource;
import com.legiz.terasoftproject.userProfile.resource.UpdateLawyerResource;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class LawyerMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    // Object Mapping with Subscription
    public LawyerSubscriptionResource toResource(Lawyer model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, LawyerSubscriptionResource.class);
    }

    public Page<LawyerSubscriptionResource> modelListToPage(List<Lawyer> modelList, Pageable pageable) {
        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, LawyerSubscriptionResource.class), pageable, modelList.size());
    }

    //public Lawyer toModel(CreateLawyerResource resource) {
      //  return mapper.map(resource, Lawyer.class);
    //}

    public Lawyer toModel(UpdateLawyerResource resource) {
        return mapper.map(resource, Lawyer.class);
    }
}

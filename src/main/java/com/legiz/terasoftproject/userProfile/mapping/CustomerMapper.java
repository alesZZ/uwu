package com.legiz.terasoftproject.userProfile.mapping;

import com.legiz.terasoftproject.security.domain.model.entity.Role;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
import com.legiz.terasoftproject.userProfile.resource.UpdateCustomerResource;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CustomerMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };
    // Object Mapping
    public CustomerResource toResource(Customer model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, CustomerResource.class);
    }

    public Page<CustomerResource> modelListToPage(List<Customer> modelList, Pageable pageable) {
        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, CustomerResource.class), pageable, modelList.size());
    }


    // Old
    //public Customer toModel(CreateCustomerResource resource) {
        //return mapper.map(resource, Customer.class);
    //}
    public Customer toModel(UpdateCustomerResource resource) { return mapper.map(resource, Customer.class); }
}

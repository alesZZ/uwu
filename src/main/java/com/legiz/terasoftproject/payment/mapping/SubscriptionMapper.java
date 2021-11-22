package com.legiz.terasoftproject.payment.mapping;

import com.legiz.terasoftproject.payment.domain.model.entity.Subscription;
import com.legiz.terasoftproject.payment.resource.CreateSubscriptionResource;
import com.legiz.terasoftproject.payment.resource.SubscriptionResource;
import com.legiz.terasoftproject.payment.resource.UpdateSubscriptionResource;
import com.legiz.terasoftproject.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SubscriptionMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public SubscriptionResource toResource(Subscription model) { return mapper.map(model, SubscriptionResource.class); }

    public Page<SubscriptionResource> modelListToPage(List<Subscription> modelList, Pageable pageable) {
        return new PageImpl<SubscriptionResource>(mapper.mapList(modelList, SubscriptionResource.class),
                pageable,
                modelList.size());
    }

    public Subscription toModel(CreateSubscriptionResource resource) {
        return mapper.map(resource, Subscription.class);
    }

    public Subscription toModel(UpdateSubscriptionResource resource) { return mapper.map(resource, Subscription.class); }
}

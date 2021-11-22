package com.legiz.terasoftproject.userProfile.domain.service.communication;

import com.legiz.terasoftproject.shared.domain.service.communication.BaseResponse;
import com.legiz.terasoftproject.userProfile.resource.CustomerResource;

public class RegisterCustomerResponse extends BaseResponse<CustomerResource> {
    public RegisterCustomerResponse(String message) {
        super(message);
    }

    public RegisterCustomerResponse(CustomerResource resource) {
        super(resource);
    }
}

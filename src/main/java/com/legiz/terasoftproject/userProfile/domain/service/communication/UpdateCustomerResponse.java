package com.legiz.terasoftproject.userProfile.domain.service.communication;

import com.legiz.terasoftproject.shared.domain.service.communication.BaseResponse;

public class UpdateCustomerResponse extends BaseResponse<UpdateCustomerResponse> {
    public UpdateCustomerResponse(String message) {
        super(message);
    }

    public UpdateCustomerResponse(UpdateCustomerResponse resource) {
        super(resource);
    }
}

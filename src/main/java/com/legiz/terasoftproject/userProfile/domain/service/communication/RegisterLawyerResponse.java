package com.legiz.terasoftproject.userProfile.domain.service.communication;

import com.legiz.terasoftproject.shared.domain.service.communication.BaseResponse;
import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerSubscriptionResource;

public class RegisterLawyerResponse extends BaseResponse<LawyerSubscriptionResource> {
    public RegisterLawyerResponse(String message) {
        super(message);
    }

    public RegisterLawyerResponse(LawyerSubscriptionResource resource) {
        super(resource);
    }
}
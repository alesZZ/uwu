package com.legiz.terasoftproject.security.domain.service.communication;

import com.legiz.terasoftproject.security.resource.AuthenticateResource;
import com.legiz.terasoftproject.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }
    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}

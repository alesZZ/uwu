package com.legiz.terasoftproject.userProfile.resource;

import com.legiz.terasoftproject.payment.resource.SubscriptionResource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class LawyerSubscriptionResource {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String lawyerName;
    private String lawyerLastName;
    private SubscriptionResource subscription;
    private List<String> roles;
}

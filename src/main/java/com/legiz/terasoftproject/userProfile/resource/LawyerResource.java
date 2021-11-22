package com.legiz.terasoftproject.userProfile.resource;

import com.legiz.terasoftproject.payment.resource.SubscriptionResource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class LawyerResource {
    private Long id;
    private String username;
    private String email;
    private String lawyerName;
    private String lawyerLastName;
    private String specialization;
    private Long priceLegalAdvice;
    private Long priceCustomLegalCase;
    private List<String> roles;
    private int subscriptionId;
}

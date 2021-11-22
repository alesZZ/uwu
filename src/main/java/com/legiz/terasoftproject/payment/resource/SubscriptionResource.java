package com.legiz.terasoftproject.payment.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class SubscriptionResource {
    private Long id;
    private Long price;
    private String description;
}

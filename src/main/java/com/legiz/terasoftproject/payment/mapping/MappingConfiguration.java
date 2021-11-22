package com.legiz.terasoftproject.payment.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("paymentMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public SubscriptionMapper subscriptionMapper() {
        return new SubscriptionMapper();
    }
}

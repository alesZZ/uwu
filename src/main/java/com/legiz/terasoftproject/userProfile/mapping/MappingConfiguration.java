package com.legiz.terasoftproject.userProfile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("userProfileMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public LawyerMapper lawyerMapper() {
        return new LawyerMapper();
    }

    @Bean
    public CustomerMapper customerMapper() { return new CustomerMapper(); }
}

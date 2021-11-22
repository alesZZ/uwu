package com.legiz.terasoftproject.qualification.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("qualificationMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public QualificationMapper qualificationMapper() { return new QualificationMapper(); }
}

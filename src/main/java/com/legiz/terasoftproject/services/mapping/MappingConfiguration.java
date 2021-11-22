package com.legiz.terasoftproject.services.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("serviceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public LegalAdviceMapper legalAdviceMapper() {
        return new LegalAdviceMapper();
    }

    @Bean
    public CustomLegalCaseMapper customLegalCaseMapper() { return new CustomLegalCaseMapper(); }

    @Bean
    public LawDocumentMapper lawDocumentMapper() { return new LawDocumentMapper(); }
}

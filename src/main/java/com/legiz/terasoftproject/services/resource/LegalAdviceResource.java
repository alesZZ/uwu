package com.legiz.terasoftproject.services.resource;

import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerResource;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class LegalAdviceResource {
    private Long id;
    private String statusLawService;
    private String title;
    private String description;
    private String legalResponse;
    private LawyerResource lawyer;
    private CustomerResource customer;
    private LawDocumentResource lawDocument;
    private Long qualificationId;
}

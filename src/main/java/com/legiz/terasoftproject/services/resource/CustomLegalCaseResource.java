package com.legiz.terasoftproject.services.resource;

import com.legiz.terasoftproject.userProfile.resource.CustomerResource;
import com.legiz.terasoftproject.userProfile.resource.LawyerResource;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CustomLegalCaseResource {
    private Long id;
    private String statusLawService;
    private String title;
    private String startAt;
    private String finishAt;
    private String typeMeet;
    private String linkZoom;
    private LawyerResource lawyer;
    private CustomerResource customer;
    private LawDocumentResource lawDocument;
    private Long qualificationId;
}

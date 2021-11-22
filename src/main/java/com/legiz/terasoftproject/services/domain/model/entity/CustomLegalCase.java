package com.legiz.terasoftproject.services.domain.model.entity;

import com.legiz.terasoftproject.services.domain.model.enumerator.StatusLawService;
import com.legiz.terasoftproject.services.domain.model.enumerator.TypeMeet;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("law_service_custom-legal-case")
public class CustomLegalCase extends LawService{

    @NotNull
    @NotBlank
    private String startAt;

    @NotNull
    @NotBlank
    private String finishAt;

    @Enumerated(EnumType.STRING)
    private TypeMeet typeMeet;

    private String linkZoom;

    public CustomLegalCase(Long id, StatusLawService statusLawService, @NotBlank @NotNull String title, Lawyer lawyer, Customer customer, LawDocument lawDocument, @NotNull @NotBlank String startAt, @NotNull @NotBlank String finishAt, TypeMeet typeMeet, String linkZoom) {
        super(id, statusLawService, title, lawyer, customer, lawDocument);
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.typeMeet = typeMeet;
        this.linkZoom = linkZoom;
    }


}

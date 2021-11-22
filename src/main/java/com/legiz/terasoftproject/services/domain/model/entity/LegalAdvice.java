package com.legiz.terasoftproject.services.domain.model.entity;

import com.legiz.terasoftproject.services.domain.model.enumerator.StatusLawService;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("law_service_legal-advice")
public class LegalAdvice extends LawService{

    @NotNull
    @NotBlank
    @Lob
    private String description;

    private String legalResponse;

    public LegalAdvice(Long id, StatusLawService statusLawService, @NotBlank @NotNull String title, Lawyer lawyer, Customer customer, LawDocument lawDocument, @NotNull @NotBlank String description, String legalResponse) {
        super(id, statusLawService, title, lawyer, customer, lawDocument);
        this.description = description;
        this.legalResponse = legalResponse;
    }
}

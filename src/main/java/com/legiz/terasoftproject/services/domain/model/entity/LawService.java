package com.legiz.terasoftproject.services.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legiz.terasoftproject.services.domain.model.enumerator.StatusLawService;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Customer;
import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "services")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "law_service_type", discriminatorType = DiscriminatorType.STRING)
public class LawService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected StatusLawService statusLawService;

    @NotBlank
    @NotNull
    protected String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_lawyer_id", nullable = true)
    @JsonIgnore
    protected Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_customer_id", nullable = true)
    @JsonIgnore
    protected Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "document_id", nullable = true)
    @JsonIgnore
    protected LawDocument document;
}

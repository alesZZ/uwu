package com.legiz.terasoftproject.qualification.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import com.legiz.terasoftproject.services.domain.model.entity.LegalAdvice;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "qualifications")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Range(min = 1, max = 5)
    private Long score;

    @NotNull
    @NotBlank
    @Lob
    private String comment;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "legal_advice_id", nullable = true)
    @JsonIgnore
    private LegalAdvice legalAdvice;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "custom_legal_case_id", nullable = true)
    @JsonIgnore
    private CustomLegalCase customLegalCase;
}

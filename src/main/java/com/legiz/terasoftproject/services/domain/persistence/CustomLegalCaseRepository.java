package com.legiz.terasoftproject.services.domain.persistence;

import com.legiz.terasoftproject.services.domain.model.entity.CustomLegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomLegalCaseRepository extends JpaRepository<CustomLegalCase, Long> {
}

package com.legiz.terasoftproject.services.domain.persistence;

import com.legiz.terasoftproject.services.domain.model.entity.LegalAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalAdviceRepository extends JpaRepository<LegalAdvice,Long> {
}

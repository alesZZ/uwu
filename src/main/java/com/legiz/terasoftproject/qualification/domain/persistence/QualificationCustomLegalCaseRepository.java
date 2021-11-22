package com.legiz.terasoftproject.qualification.domain.persistence;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationCustomLegalCaseRepository extends JpaRepository<Qualification, Long> {
    List<Qualification> findByCustomLegalCaseId(Long customLegalCaseId);
    Page<Qualification> findByCustomLegalCaseId(Long customLegalCaseId, Pageable pageable);
    Optional<Qualification> findByIdAndCustomLegalCaseId(Long id, Long customLegalCaseId);
}

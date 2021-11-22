package com.legiz.terasoftproject.qualification.domain.persistence;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationLegalAdviceRepository extends JpaRepository<Qualification, Long> {
    List<Qualification> findByLegalAdviceId(Long legalAdviceId);
    Page<Qualification> findByLegalAdviceId(Long legalAdviceId, Pageable pageable);
    Optional<Qualification> findByIdAndLegalAdviceId(Long id, Long legalAdviceId);
}

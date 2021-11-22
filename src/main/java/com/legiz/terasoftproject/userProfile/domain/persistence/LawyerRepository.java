package com.legiz.terasoftproject.userProfile.domain.persistence;

import com.legiz.terasoftproject.userProfile.domain.model.entity.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    Lawyer findByUsername(String username);
    Lawyer findByEmail(String email);
}

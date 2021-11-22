package com.legiz.terasoftproject.qualification.domain.service;

import com.legiz.terasoftproject.qualification.domain.model.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface QualificationCustomLegalCaseService {
    List<Qualification> getAllByCustomLegalCaseId(Long customLegalCaseId);
    Page<Qualification> getAllByCustomLegalCaseId(Long customLegalCaseId, Pageable pageable);
    Qualification create(Long customLegalCaseId, Qualification request);
    Qualification update(Long customLegalCaseId, Long qualificationId, Qualification request);
    ResponseEntity<?> delete(Long customLegalCaseId, Long qualificationId);

}

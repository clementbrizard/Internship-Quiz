package com.sr03.project.repository;

import com.sr03.project.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findById(Long id);
    Subject findAllByForms(Long id);
}

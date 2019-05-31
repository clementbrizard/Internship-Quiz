package com.sr03.project.repository;

import com.sr03.project.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
    Form findById(Long id);
}
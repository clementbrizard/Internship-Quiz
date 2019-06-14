package com.sr03.project.repository;

import com.sr03.project.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    Form findById(Long id);

    @Override
    List<Form> findAll();
    /*@Override*/
    List<Form> findByIsActiveTrue();
}
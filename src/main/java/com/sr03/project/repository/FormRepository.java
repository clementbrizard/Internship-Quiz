package com.sr03.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sr03.project.model.Form;

public interface FormRepository extends JpaRepository<Form, Long> {

}
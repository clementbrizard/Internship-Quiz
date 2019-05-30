package com.sr03.project.repository;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
    FormQuestion findById(Long id);
    FormQuestion findFormQuestionByForm(Form form);
}
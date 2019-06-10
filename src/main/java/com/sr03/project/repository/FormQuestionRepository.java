package com.sr03.project.repository;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
import com.sr03.project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormQuestionRepository extends JpaRepository<FormQuestion, Long> {
    FormQuestion findById(Long id);
    FormQuestion findFormQuestionByForm(Form form);
    FormQuestion findFormQuestionByFormAndQuestion(Form form, Question question);
    FormQuestion findFormQuestionByFormAndPosition(Form form, Integer position);
}
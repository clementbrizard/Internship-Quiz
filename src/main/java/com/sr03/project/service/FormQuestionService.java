package com.sr03.project.service;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
import com.sr03.project.model.Question;

public interface FormQuestionService {
    void save(FormQuestion form);
    FormQuestion findById(Long id);
    FormQuestion findFormQuestionByForm(Form form);
    FormQuestion findFormQuestionByFormAndQuestion(Form form, Question question);
    FormQuestion findFormQuestionByFormAndPosition(Form form, Integer position);
}

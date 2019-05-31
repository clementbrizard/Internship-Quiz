package com.sr03.project.web.editors.property;

import com.sr03.project.model.Question;
import com.sr03.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class CustomQuestionEditor extends PropertyEditorSupport {

    @Autowired
    private QuestionRepository questionRepository;

    public CustomQuestionEditor(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text != null) {
            Long id = Long.valueOf(text);
            Question question = questionRepository.findById(id);
            System.out.println(question);
            setValue(question);
        }
    }
}

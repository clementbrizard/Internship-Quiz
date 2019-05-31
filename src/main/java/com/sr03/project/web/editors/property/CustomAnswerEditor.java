package com.sr03.project.web.editors.property;

import com.sr03.project.model.Answer;
import com.sr03.project.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class CustomAnswerEditor extends PropertyEditorSupport {

    @Autowired
    private AnswerRepository answerRepository;

    public CustomAnswerEditor(AnswerRepository formRepository) {
        this.answerRepository = formRepository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text != null) {
            Long id = Long.valueOf(text);
            Answer answer = answerRepository.findById(id);
            setValue(answer);
        }
    }
}

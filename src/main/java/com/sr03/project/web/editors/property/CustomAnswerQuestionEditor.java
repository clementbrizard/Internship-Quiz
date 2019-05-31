package com.sr03.project.web.editors.property;

import com.sr03.project.model.AnswerQuestion;
import com.sr03.project.repository.AnswerQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class CustomAnswerQuestionEditor extends PropertyEditorSupport {

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;

    public CustomAnswerQuestionEditor(AnswerQuestionRepository answerQuestionRepository) {
        this.answerQuestionRepository = answerQuestionRepository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if (text != null) {
            Long id = Long.valueOf(text);
            AnswerQuestion answerQuestion = answerQuestionRepository.findById(id);
            System.out.println(answerQuestion);
            setValue(answerQuestion);
        }
    }

}

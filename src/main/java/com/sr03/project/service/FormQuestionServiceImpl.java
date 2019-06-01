package com.sr03.project.service;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
import com.sr03.project.model.Question;
import com.sr03.project.repository.FormQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormQuestionServiceImpl implements FormQuestionService {
    @Autowired
    private FormQuestionRepository formQuestionRepository;

    @Override
    public void save(FormQuestion form) {
        //form.setIsActive(true);
        // form.setSubjects(form.getSubjects());
        formQuestionRepository.save(form);
    }

    @Override
    public FormQuestion findById(Long id) {
        return formQuestionRepository.findById(id);
    }

    @Override
    public FormQuestion findFormQuestionByForm(Form form) {
        return formQuestionRepository.findFormQuestionByForm(form);
    }

    @Override
    public FormQuestion findFormQuestionByFormAndQuestion(Form form, Question question) {
        return formQuestionRepository.findFormQuestionByFormAndQuestion(form, question);
    }

    @Override
    public FormQuestion findFormQuestionByFormAndPosition(Form form, Integer position){
        return formQuestionRepository.findFormQuestionByFormAndPosition(form,position);
    }
}

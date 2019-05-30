package com.sr03.project.service;

import com.sr03.project.model.Form;
import com.sr03.project.model.FormQuestion;
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
    public  FormQuestion findFormQuestionByForm(Form form){
        return formQuestionRepository.findFormQuestionByForm(form);
    }
}

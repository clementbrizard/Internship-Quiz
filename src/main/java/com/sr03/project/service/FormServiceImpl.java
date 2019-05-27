package com.sr03.project.service;

import com.sr03.project.model.Form;
import com.sr03.project.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormRepository formRepository;

    @Override
    public void save(Form form) {
        form.setIsActive(true);
        form.setSubjects(form.getSubjects());
        formRepository.save(form);
    }
    @Override
    public Form findById(Long id) {
        return formRepository.findById(id);
    }
}

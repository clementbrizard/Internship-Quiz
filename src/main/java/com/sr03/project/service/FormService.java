package com.sr03.project.service;

import com.sr03.project.model.Form;

import java.util.List;

public interface FormService {
    void save(Form form);
    Form findById(Long id);
    List<Form> findAll();
    List<Form> findByIsActiveTrue();
}

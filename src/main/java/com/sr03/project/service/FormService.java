package com.sr03.project.service;

import com.sr03.project.model.Form;

public interface FormService {
    void save(Form user);
    Form findById(Long id);
}

package com.sr03.project.service;

import com.sr03.project.model.Subject;

public interface SubjectService {
    void save(Subject subject);

    Subject findById(Long id);

    Subject findAllByForms(Long id);
}

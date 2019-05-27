package com.sr03.project.service;

import com.sr03.project.model.Question;

public interface QuestionService {
    void save(Question question);
    Question findById(Long id);
}

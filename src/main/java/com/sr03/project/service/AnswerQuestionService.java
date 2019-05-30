package com.sr03.project.service;

import com.sr03.project.model.AnswerQuestion;

public interface AnswerQuestionService {
    void save(AnswerQuestion answer);
    AnswerQuestion findById(Long id);
}

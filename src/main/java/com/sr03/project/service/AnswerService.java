package com.sr03.project.service;

import com.sr03.project.model.Answer;

public interface AnswerService {
    void save(Answer answer);
    Answer findById(Long id);
}

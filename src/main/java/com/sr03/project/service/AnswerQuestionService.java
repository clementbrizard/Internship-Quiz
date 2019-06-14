package com.sr03.project.service;

import com.sr03.project.model.Answer;
import com.sr03.project.model.AnswerQuestion;
import com.sr03.project.model.Question;

public interface AnswerQuestionService {
    void save(AnswerQuestion answer);
    AnswerQuestion findById(Long id);
    AnswerQuestion findByQuestionAndPosition(Question question, Integer position);
}

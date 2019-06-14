package com.sr03.project.service;

import com.sr03.project.model.Answer;
import com.sr03.project.model.AnswerQuestion;
import com.sr03.project.model.Question;
import com.sr03.project.repository.AnswerQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerQuestionServiceImpl implements AnswerQuestionService {
    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;

    @Override
    public void save(AnswerQuestion answer) {
        answerQuestionRepository.save(answer);
    }

    @Override
    public AnswerQuestion findById(Long id) {
        return answerQuestionRepository.findById(id);
    }

    @Override
    public AnswerQuestion findByQuestionAndPosition(Question question, Integer position) {
        return answerQuestionRepository.findByQuestionAndPosition(question, position);
    }


}

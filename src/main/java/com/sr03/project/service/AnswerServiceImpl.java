package com.sr03.project.service;

import com.sr03.project.model.Answer;
import com.sr03.project.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }
    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id);
    }
}

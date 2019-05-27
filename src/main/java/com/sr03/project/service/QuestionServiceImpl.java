package com.sr03.project.service;

import com.sr03.project.model.Question;
import com.sr03.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }
    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id);
    }
}

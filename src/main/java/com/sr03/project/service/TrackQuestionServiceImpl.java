package com.sr03.project.service;

import com.sr03.project.model.TrackQuestion;
import com.sr03.project.repository.TrackQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackQuestionServiceImpl implements TrackQuestionService {
    @Autowired
    private TrackQuestionRepository trackQuestionRepository;

    @Override
    public void save(TrackQuestion trackQuestion) {
        trackQuestionRepository.save(trackQuestion);
    }

    @Override
    public TrackQuestion findById(Long id) { return trackQuestionRepository.findById(id); }

}

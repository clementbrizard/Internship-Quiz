package com.sr03.project.service;

import com.sr03.project.model.TrackQuestion;

public interface TrackQuestionService {
    void save(TrackQuestion track);
    TrackQuestion findById(Long id);
}

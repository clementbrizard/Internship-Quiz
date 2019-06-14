package com.sr03.project.repository;

import com.sr03.project.model.TrackQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackQuestionRepository extends JpaRepository<TrackQuestion, Long> {
    TrackQuestion findById(Long id);
}

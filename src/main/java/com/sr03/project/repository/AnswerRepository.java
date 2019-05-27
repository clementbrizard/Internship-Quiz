package com.sr03.project.repository;

import com.sr03.project.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findById(Long id);
}
package com.sr03.project.repository;

import com.sr03.project.model.AnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerQuestionRepository extends JpaRepository<AnswerQuestion, Long> {
    AnswerQuestion findById(Long id);
}
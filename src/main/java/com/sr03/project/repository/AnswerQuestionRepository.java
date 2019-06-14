package com.sr03.project.repository;

import com.sr03.project.model.AnswerQuestion;
import com.sr03.project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerQuestionRepository extends JpaRepository<AnswerQuestion, Long> {
    AnswerQuestion findById(Long id);
    AnswerQuestion findByQuestionAndPosition(Question question, Integer position);
}
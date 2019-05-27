package com.sr03.project.repository;

import com.sr03.project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(Long id);

}
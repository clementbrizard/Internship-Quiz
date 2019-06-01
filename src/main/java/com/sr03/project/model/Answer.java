package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Boolean isActive;

    @OneToMany(targetEntity = AnswerQuestion.class,
            mappedBy = "answer",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    public Set<AnswerQuestion> answerQuestion  = new HashSet<AnswerQuestion>(0);

    public Answer(String title, Boolean isActive) {
        this.title = title;
        this.isActive = isActive;
    }

    public Answer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Set<AnswerQuestion> getAnswerQuestion() {
        return answerQuestion;
    }

    public void setAnswerQuestion(Set<AnswerQuestion> answerQuestion) {
        this.answerQuestion = answerQuestion;
    }
}

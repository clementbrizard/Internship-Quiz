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
    private Integer id;
    @NotNull
    private String text;
    @NotNull
    private Boolean isActive;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
    public Set<AnswerQuestion> answerQuestion  = new HashSet<AnswerQuestion>(0);
/*    @ManyToMany(mappedBy = "answers")
    private Question questions;*/

    public Answer(String text, Boolean isActive) {
        this.text = text;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<AnswerQuestion> getAnswerQuestion() {
        return answerQuestion;
    }

    public void setAnswerQuestion(Set<AnswerQuestion> answerQuestion) {
        this.answerQuestion = answerQuestion;
    }
}

package com.sr03.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AnswerQuestion")
public class AnswerQuestion {
    @Id
    private Answer answer;
    @Id
    private Question question;
    @NotNull
    private Integer position;
    @NotNull
    private Boolean isValid;

    public AnswerQuestion(Answer answer, Question question, Integer position, Boolean isValid) {
        this.answer = answer;
        this.question = question;
        this.position = position;
        this.isValid = isValid;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}

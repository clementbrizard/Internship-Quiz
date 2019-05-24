package com.sr03.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "QuestionSubject")
public class QuestionSubject {
    @Id
    private Question question;
    @Id
    private Subject subject;

    public QuestionSubject(Question question, Subject subject) {
        this.question = question;
        this.subject = subject;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

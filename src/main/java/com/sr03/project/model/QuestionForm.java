package com.sr03.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "QuestionForm")
public class QuestionForm {
    @Id
    private Form form;
    @Id
    private Question question;
    @NotNull
    private Integer position;

    public QuestionForm(Form form, Question question, Integer position) {
        this.form = form;
        this.question = question;
        this.position = position;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
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
}

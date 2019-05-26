package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "form_question")
public class FormQuestion {
    @Id
    @GeneratedValue
    @Column(name = "form_question_id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="form_id")
    private Form form;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="question_id")
    private Question question;
    @NotNull
    private Integer position;

    public FormQuestion(Integer position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

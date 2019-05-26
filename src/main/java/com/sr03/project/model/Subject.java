package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Integer id;
    @NotNull
    private String text;
    @ManyToMany(mappedBy = "subjects")
    private Set<Question> questions;

    public Subject(String text) {
        this.text = text;
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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}

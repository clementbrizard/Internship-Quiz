package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;
    @NotNull
    private String title;
    @ManyToMany(mappedBy = "subjects")
    private List<Question> questions;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "subjects")
    private List<Form> forms;

    public Subject(){

    }

    public Subject(String title, List<Question> questions, List<Form> forms) {
        this.title = title;
        this.questions = questions;
        this.forms = forms;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

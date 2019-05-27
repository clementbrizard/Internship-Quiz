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
    private Long id;
    @NotNull
    private String title;
    @ManyToMany(mappedBy = "subjects")
    private Set<Question> questions;
    @ManyToMany(mappedBy = "subjects")
    private Set<Form> forms;

    public Subject(){

    }

    public Subject(String title, Set<Question> questions, Set<Form> forms) {
        this.title = title;
        this.questions = questions;
        this.forms = forms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}

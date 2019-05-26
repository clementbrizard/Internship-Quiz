package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "form")
public class Form {
    @Id
    @GeneratedValue
    @Column(name = "form_id")
    private Integer id;
    @NotNull
    private Integer subject;
    @NotNull
    private String title;
    @NotNull
    private Boolean isActive;
    @OneToMany(mappedBy = "form")
    public Set<FormQuestion> formQuestion  = new HashSet<FormQuestion>(0);
    @OneToMany(mappedBy = "form")
    public Set<Track> track  = new HashSet<>(0);

    public Form(Integer subject, String title) {
        this.subject = subject;
        this.title = title;
        this.isActive = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<FormQuestion> getFormQuestion() {
        return formQuestion;
    }

    public void setFormQuestion(Set<FormQuestion> formQuestion) {
        this.formQuestion = formQuestion;
    }

    public Set<Track> getTrack() {
        return track;
    }

    public void setTrack(Set<Track> track) {
        this.track = track;
    }
}

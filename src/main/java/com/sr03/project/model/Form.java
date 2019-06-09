package com.sr03.project.model;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue
    @Column(name = "form_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Boolean isActive;

    @OneToMany(targetEntity = FormQuestion.class,
            mappedBy = "form",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @SortNatural
    @OrderBy("position ASC")
    private SortedSet<FormQuestion> formQuestion;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "form")
    private Set<Track> track  = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "form_subject", joinColumns = @JoinColumn(name = "form_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<Subject>(0);

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

    public SortedSet<FormQuestion> getFormQuestion() {
        return formQuestion;
    }

    public void setFormQuestion(SortedSet<FormQuestion> formQuestion) {
        this.formQuestion = formQuestion;
    }

    public Set<Track> getTrack() {
        return track;
    }

    public void setTrack(Set<Track> track) {
        this.track = track;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }


}

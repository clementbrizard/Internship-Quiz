package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;
    @NotNull
    private String text;
    @NotNull
    private Boolean isActive;
    @OneToMany(mappedBy = "question")
    private Set<AnswerQuestion> answerQuestion = new HashSet<>(0);
    @OneToMany(mappedBy = "question")
    private Set<TrackQuestion> trackQuestion = new HashSet<>(0);
    @OneToMany(mappedBy = "question")
    private Set<FormQuestion> formQuestion = new HashSet<>(0);
    @ManyToMany
    @JoinTable(name = "subject_question", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;
/*    @ManyToMany
    @JoinTable(name = "question_answer", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answers;*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<TrackQuestion> getTrackQuestion() {
        return trackQuestion;
    }

    public void setTrackQuestion(Set<TrackQuestion> trackQuestion) {
        this.trackQuestion = trackQuestion;
    }

    public Set<FormQuestion> getFormQuestion() {
        return formQuestion;
    }

    public void setFormQuestion(Set<FormQuestion> formQuestion) {
        this.formQuestion = formQuestion;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}


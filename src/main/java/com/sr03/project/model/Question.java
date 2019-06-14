package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Boolean isActive;

    @OneToMany(targetEntity = FormQuestion.class,
            mappedBy = "question",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private Set<FormQuestion> formQuestion = new HashSet<FormQuestion>(0);

    @OneToMany(targetEntity = AnswerQuestion.class,
            mappedBy = "question",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    @OrderBy("position ASC")
    private SortedSet<AnswerQuestion> answerQuestion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<TrackQuestion> trackQuestion = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "subject_question", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<Subject>(0);
/*    @ManyToMany
    @JoinTable(name = "question_answer", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answers;*/


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

    public SortedSet<AnswerQuestion> getAnswerQuestion() {
        return answerQuestion;
    }

    public void setAnswerQuestion(SortedSet<AnswerQuestion> answerQuestion) {
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


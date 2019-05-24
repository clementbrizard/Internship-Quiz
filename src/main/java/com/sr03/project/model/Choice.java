package com.sr03.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Choice")
public class Choice {
    @Id
    private Track track;
    @Id
    private Question question;
    @NotNull
    private Integer choicePosition;

    public Choice(Track track, Question question, Integer choicePosition) {
        this.track = track;
        this.question = question;
        this.choicePosition = choicePosition;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getChoicePosition() {
        return choicePosition;
    }

    public void setChoicePosition(Integer choicePosition) {
        this.choicePosition = choicePosition;
    }
}

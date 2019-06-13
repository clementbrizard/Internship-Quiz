package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "track_question")
public class TrackQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "track_question_id")
    private Long id;

    @NotNull
    private Integer choicePosition = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="track_id")
    private Track track;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Integer getChoicePosition() {
        return choicePosition;
    }

    public void setChoicePosition(Integer choicePosition) {
        this.choicePosition = choicePosition;
    }
}

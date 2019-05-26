package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "track_question")
public class TrackQuestion {
    @Id
    @GeneratedValue
    @Column(name = "track_question_id")
    private Integer id;
    @NotNull
    private Integer choicePosition;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="question_id")
    private Question question;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="track_id")
    private Track track;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChoicePosition() {
        return choicePosition;
    }

    public void setChoicePosition(Integer choicePosition) {
        this.choicePosition = choicePosition;
    }
}

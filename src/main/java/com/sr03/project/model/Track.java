package com.sr03.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue
    @Column(name = "track_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="form_id")
    private Form form;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;
    @NotNull
    private Double score;
    @NotNull
    private Integer duration;
    @OneToMany(mappedBy = "track")
    public Set<TrackQuestion> trackQuestion  = new HashSet<TrackQuestion>(0);

    public Track(Form form, User user, Double score, Integer duration) {
        this.form = form;
        this.user = user;
        this.score = score;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<TrackQuestion> getTrackQuestion() {
        return trackQuestion;
    }

    public void setTrackQuestion(Set<TrackQuestion> trackQuestion) {
        this.trackQuestion = trackQuestion;
    }

}

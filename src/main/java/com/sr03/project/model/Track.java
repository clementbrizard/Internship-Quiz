package com.sr03.project.model;

import com.sr03.project.web.editors.property.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue
    @Column(name = "track_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="form_id")
    private Form form;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @NotNull
    private Double score = 0.;

    @Column(name = "date")
    // @Convert(converter = LocalDateTimeAttributeConverter.class)
    private String date = LocalDateTime.now().toString();

    @Column(name = "duration")
    // @Convert(converter = LocalDateTimeAttributeConverter.class)
    private String duration = LocalDateTime.now().toString();

    @OneToMany(targetEntity = TrackQuestion.class,
            mappedBy = "track",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public Set<TrackQuestion> trackQuestion  = new HashSet<TrackQuestion>(0);

    public Track() {}

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<TrackQuestion> getTrackQuestion() {
        return trackQuestion;
    }

    public void setTrackQuestion(Set<TrackQuestion> trackQuestion) {
        this.trackQuestion = trackQuestion;
    }

}

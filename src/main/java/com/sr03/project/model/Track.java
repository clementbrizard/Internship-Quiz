package com.sr03.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.sr03.project.model.User;
import com.sr03.project.model.Form;

@Entity
@Table(name = "Track")
public class Track {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private Form form;
    @NotNull
    private User user;
    @NotNull
    private Double score;
    @NotNull
    private Integer duration;

    public Track(Form form, User user, Double score, Integer duration) {
        this.form = form;
        this.user = user;
        this.score = score;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}

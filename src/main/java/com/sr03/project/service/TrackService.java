package com.sr03.project.service;

import com.sr03.project.model.Form;
import com.sr03.project.model.Track;
import com.sr03.project.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TrackService {
    void save(Track track);
    Track findById(Long id);
    Track findByFormAndUserAndDate(Form form, User user, String date);
    List<Track> findByUser(User user);

}


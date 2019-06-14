package com.sr03.project.service;

import com.sr03.project.model.Form;
import com.sr03.project.model.Track;
import com.sr03.project.model.User;
import com.sr03.project.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private TrackRepository trackRepository;

    @Override
    public void save(Track track) {
        trackRepository.save(track);
    }

    @Override
    public Track findById(Long id) { return trackRepository.findById(id); }

    @Override
    public Track findByFormAndUserAndDate(Form form, User user, String date) { return trackRepository.findByFormAndUserAndDate(form, user, date); }

    @Override
    public List<Track> findByUser(User user) { return trackRepository.findByUser(user); }

}

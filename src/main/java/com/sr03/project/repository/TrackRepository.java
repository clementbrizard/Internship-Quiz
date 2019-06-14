package com.sr03.project.repository;

import com.sr03.project.model.Form;
import com.sr03.project.model.Track;
import com.sr03.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrackRepository  extends JpaRepository<Track, Long> {
    Track findById(Long id);
    Track findByFormAndUserAndDate(Form form, User user, String date);
    List<Track> findByUser(User user);
}

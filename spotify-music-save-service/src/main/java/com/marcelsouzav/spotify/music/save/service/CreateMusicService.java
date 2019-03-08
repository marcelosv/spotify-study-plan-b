package com.marcelsouzav.spotify.music.save.service;

import com.marcelsouzav.spotify.music.save.domain.Music;
import com.marcelsouzav.spotify.music.save.gateway.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateMusicService {

    @Autowired
    private MusicRepository musicRepository;

    public UUID execute(Music music) {
        music.setId(UUID.randomUUID());
        musicRepository.save(music);
        return music.getId();
    }

}

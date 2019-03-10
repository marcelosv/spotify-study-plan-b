package com.marcelsouzav.spotify.music.listener.service;

import com.marcelsouzav.spotify.json.MusicJson;
import com.marcelsouzav.spotify.music.listener.domain.Music;
import com.marcelsouzav.spotify.music.listener.gateway.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetMusicService {

    @Autowired
    private MusicRepository musicRepository;

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public MusicJson execute(MusicJson musicJson) {
        Optional<Music> music = musicRepository.findById(UUID.fromString(musicJson.getUuid()));
        return MusicJson
                .builder()
                .path(music.get().getPath())
                .size(music.get().getSize())
                .build();
    }

}

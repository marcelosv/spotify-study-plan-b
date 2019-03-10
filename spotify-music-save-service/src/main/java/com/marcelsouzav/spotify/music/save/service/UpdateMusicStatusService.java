package com.marcelsouzav.spotify.music.save.service;

import com.marcelsouzav.spotify.json.MusicJson;
import com.marcelsouzav.spotify.music.save.domain.Music;
import com.marcelsouzav.spotify.music.save.enums.MusicStatusEnums;
import com.marcelsouzav.spotify.music.save.gateway.repository.MusicRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateMusicStatusService {

    @Autowired
    private MusicRepository musicRepository;

    @KafkaListener(topics = "${kafka.topic.music-status}", groupId = "${kafka.consumergroup}")
    public void execute(Object musicUpdate) {

        ConsumerRecord musicConsumer = (ConsumerRecord) musicUpdate;

        MusicJson musicUpdateStatusJson = (MusicJson) musicConsumer.value();

        Optional<Music> music = musicRepository.findById(UUID.fromString(musicUpdateStatusJson.getUuid()));

        music.get().setStatus(MusicStatusEnums.READY.toString());
        music.get().setPath(musicUpdateStatusJson.getPath());
        music.get().setSize(musicUpdateStatusJson.getSize());

        musicRepository.save(music.get());
    }

}

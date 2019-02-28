package com.marcelsouzav.spotify.music.save.service;

import com.marcelsouzav.spotify.json.MusicJson;
import com.marcelsouzav.spotify.music.save.domain.Music;
import com.marcelsouzav.spotify.music.save.enums.MusicStatusEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateMusicListener {

    @Autowired
    private CreateMusicService createMusicService;

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public MusicJson listen(MusicJson musicJson) throws InterruptedException {
        UUID uuid = createMusicService.execute(Music
                .builder()
                .name(musicJson.getName())
                .status(MusicStatusEnums.WAIT_SAVE_PATH.toString())
                .uuidCustomer(musicJson.getUuidCustomer())
                .build()
        );
        musicJson.setUuid(uuid.toString());
        return musicJson;
    }

}

package com.marcelsouzav.spotify.music.upload.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class UploadMusicService {

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.header.uuid-customer")
    String headerCustomer;

    @Value("${kafka.header.uuid-music")
    String headerMusic;

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    public void listen(@Payload ByteArrayInputStream data,
                       @Headers MessageHeaders messageHeaders) throws InterruptedException {

        byte[] uuidCustomer = (byte[]) messageHeaders.get(headerCustomer);
        byte[] uuidMusic = (byte[]) messageHeaders.get(headerMusic);

        "".toCharArray();
    }

}

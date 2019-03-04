package com.marcelsouzav.spotify.music.upload.service.service;

import com.marcelsouzav.spotify.json.MusicUploadJson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UploadMusicService {

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.header.uuid-customer")
    String headerCustomer;

    @Value("${kafka.header.uuid-music")
    String headerMusic;

    public void execute(MusicUploadJson musicUploadJson) throws IOException {
        ProducerRecord<String, byte[]> producerRecord = new ProducerRecord(requestTopic, musicUploadJson.getFile().getBytes(), musicUploadJson.getFile().getBytes());
        producerRecord.headers().add(new RecordHeader(headerCustomer, musicUploadJson.getUuid().getBytes()));
        producerRecord.headers().add(new RecordHeader(headerMusic, musicUploadJson.getUuidMusic().getBytes()));

        kafkaTemplate.send(producerRecord);
    }
}

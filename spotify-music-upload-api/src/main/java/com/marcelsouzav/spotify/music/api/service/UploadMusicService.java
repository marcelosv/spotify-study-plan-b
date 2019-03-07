package com.marcelsouzav.spotify.music.api.service;

import com.marcelsouzav.spotify.json.MusicUploadJson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UploadMusicService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.header.uuid-customer")
    String headerCustomer;

    @Value("${kafka.header.uuid-music")
    String headerMusic;

    public void execute(MusicUploadJson musicUploadJson) throws IOException {

        Bytes bytes = new org.apache.kafka.common.utils.Bytes(musicUploadJson.getFile().getBytes());

        ProducerRecord<Bytes, Bytes> producerRecord = new ProducerRecord(requestTopic, bytes, bytes);
        producerRecord.headers().add(new RecordHeader(headerCustomer, musicUploadJson.getUuid().getBytes()));
        producerRecord.headers().add(new RecordHeader(headerMusic, musicUploadJson.getUuidMusic().getBytes()));

        /*Message<Bytes> message = MessageBuilder
                .withPayload(bytes)
                .setHeader(KafkaHeaders.TOPIC, requestTopic)
                .setHeader(headerCustomer, musicUploadJson)
                .setHeader(headerMusic, musicUploadJson)
                .build();*/

        kafkaTemplate.send(producerRecord);
    }
}

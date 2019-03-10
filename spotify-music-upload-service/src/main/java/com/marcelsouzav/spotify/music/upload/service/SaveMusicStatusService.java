package com.marcelsouzav.spotify.music.upload.service;

import com.marcelsouzav.spotify.json.MusicJson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SaveMusicStatusService {

    @Autowired
    @Qualifier("templateUpdate")
    private KafkaTemplate template;

    @Value("${kafka.topic.music-status}")
    private String topicMusicStatus;

    public void execute(MusicJson music) {
        ProducerRecord<MusicJson, MusicJson> producerRecord = new ProducerRecord(topicMusicStatus, music, music);
        template.send(producerRecord);
    }

}

package com.marcelsouzav.spotify.music.save.api.service;

import com.marcelsouzav.spotify.json.MusicJson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SaveMusicService {

    @Autowired
    ReplyingKafkaTemplate<String, MusicJson, MusicJson> kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;

    public String execute(MusicJson customerJson) throws ExecutionException, InterruptedException {

        ProducerRecord<String, MusicJson> record = new ProducerRecord<String, MusicJson>(requestTopic, customerJson);

        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        RequestReplyFuture<String, MusicJson, MusicJson> sendAndReceive = kafkaTemplate.sendAndReceive(record);

        SendResult<String, MusicJson> sendResult = sendAndReceive.getSendFuture().get();

        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        ConsumerRecord<String, MusicJson> consumerRecord = sendAndReceive.get();

        return consumerRecord.value().getUuid();
    }
}

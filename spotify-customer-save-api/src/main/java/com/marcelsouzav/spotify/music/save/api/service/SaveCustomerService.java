package com.marcelsouzav.spotify.music.save.api.service;

import com.marcelsouzav.spotify.json.CustomerJson;
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
public class SaveCustomerService {

    @Autowired
    ReplyingKafkaTemplate<String, CustomerJson, CustomerJson> kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;

    public String execute(CustomerJson customerJson) throws ExecutionException, InterruptedException {

        ProducerRecord<String, CustomerJson> record = new ProducerRecord<String, CustomerJson>(requestTopic, customerJson);

        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        RequestReplyFuture<String, CustomerJson, CustomerJson> sendAndReceive = kafkaTemplate.sendAndReceive(record);

        SendResult<String, CustomerJson> sendResult = sendAndReceive.getSendFuture().get();

        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        ConsumerRecord<String, CustomerJson> consumerRecord = sendAndReceive.get();

        return consumerRecord.value().getUuid();
    }
}

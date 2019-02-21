package com.marcelsouzav.spotify.customer.save.api.gateway.http;

import com.marcelsouzav.spotify.customer.save.api.json.CustomerJson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    ReplyingKafkaTemplate<String, CustomerJson, CustomerJson> kafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;

    @PostMapping("/")
    public String create(@RequestBody CustomerJson customerJson) throws ExecutionException, InterruptedException {

        ProducerRecord<String, CustomerJson> record = new ProducerRecord<String, CustomerJson>(requestTopic, customerJson);

        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        RequestReplyFuture<String, CustomerJson, CustomerJson> sendAndReceive = kafkaTemplate.sendAndReceive(record);


        SendResult<String, CustomerJson> sendResult = sendAndReceive.getSendFuture().get();

        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        ConsumerRecord<String, CustomerJson> consumerRecord = sendAndReceive.get();

        return consumerRecord.value().toString();

    }


}

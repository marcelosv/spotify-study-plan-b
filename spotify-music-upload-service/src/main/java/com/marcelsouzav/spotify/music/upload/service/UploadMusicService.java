package com.marcelsouzav.spotify.music.upload.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadMusicService {

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.header.uuid-customer")
    String headerCustomer;

    @Value("${kafka.header.uuid-music")
    String headerMusic;

    @KafkaListener(topics = "${kafka.topic.request-topic}", groupId = "${kafka.consumer.group-id}")
    public void listen(@Payload ConsumerRecord record,
                       @Headers MessageHeaders messageHeaders) throws InterruptedException, IOException {

        byte[] uuidCustomer = (byte[]) messageHeaders.get(headerCustomer);
        byte[] uuidMusic = (byte[]) messageHeaders.get(headerMusic);

        String uuidCustomerStr = new String(uuidCustomer, "UTF-8");
        String uuidMusicStr = new String(uuidMusic, "UTF-8");

        Bytes aByte = (Bytes) record.value();



        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAIOSFODNN7EXAMPLE",
                "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");

        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration().withSignerOverride("S3SignerType"));

        newClient.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        newClient.setEndpoint("http://localhost:9444/s3");

        Path path = Paths.get("C:\\myfile.pdf");
        Files.write(path, aByte.get());

        newClient.putObject("mp3", uuidMusicStr+".mp3", path.toFile());


    }

}

package com.marcelsouzav.spotify.music.save.service;

import com.marcelsouzav.spotify.music.save.domain.Customer;
import com.marcelsouzav.spotify.json.CustomerJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCustomerListener {

    @Autowired
    private CreateCustomerService createCustomerService;

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public CustomerJson listen(CustomerJson customerJson) throws InterruptedException {
        UUID uuid = createCustomerService.execute(Customer
                .builder()
                .country(customerJson.getCountry())
                .musicStyle(customerJson.getMusicStyle())
                .name(customerJson.getName())
                .build()
        );
        customerJson.setUuid(uuid.toString());
        return customerJson;
    }

}

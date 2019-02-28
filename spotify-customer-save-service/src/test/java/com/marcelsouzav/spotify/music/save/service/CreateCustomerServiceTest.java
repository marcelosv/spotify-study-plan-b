package com.marcelsouzav.spotify.music.save.service;

import com.marcelsouzav.spotify.music.save.gateway.repository.CustomerRepository;
import com.marcelsouzav.spotify.music.save.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CreateCustomerService createCustomerService;

    @Test
    public void create(){
        createCustomerService.execute(Customer
                .builder()
                .country("BRAZIL")
                .name("Marcelo")
                .musicStyle("ROCK")
                .build()
        );
    }

}

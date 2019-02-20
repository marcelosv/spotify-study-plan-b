package com.marcelsouzav.spotifycustomersave.service;

import com.marcelsouzav.spotifycustomersave.domain.Customer;
import com.marcelsouzav.spotifycustomersave.gateway.repository.CustomerRepository;
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

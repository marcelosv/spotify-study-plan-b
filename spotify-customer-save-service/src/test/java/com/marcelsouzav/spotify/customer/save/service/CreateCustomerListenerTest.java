package com.marcelsouzav.spotify.customer.save.service;

import com.marcelsouzav.spotify.json.CustomerJson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomerListenerTest {

    @Mock
    private CreateCustomerService createCustomerService;

    @InjectMocks
    private CreateCustomerListener createCustomerListener;

    @Test
    public void create() throws InterruptedException {

        Mockito.when(createCustomerService.execute(org.mockito.Matchers.any())).thenReturn(UUID.randomUUID());

        CustomerJson json = CustomerJson
                .builder()
                .country("BRAZIL")
                .name("Marcelo")
                .musicStyle("ROCK")
                .build();

        CustomerJson listen = createCustomerListener.listen(json);
        Assert.assertNotNull(listen.getUuid());
    }
}

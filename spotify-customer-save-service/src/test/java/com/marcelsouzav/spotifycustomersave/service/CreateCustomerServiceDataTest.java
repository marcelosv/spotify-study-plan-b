package com.marcelsouzav.spotifycustomersave.service;

import com.marcelsouzav.spotifycustomersave.domain.Customer;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionIntegrationTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({CassandraUnitDependencyInjectionIntegrationTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@CassandraDataSet(keyspace = "local_test")
@EmbeddedCassandra
public class CreateCustomerServiceDataTest {

    @Autowired
    private CreateCustomerService createCustomerService;

    @Test
    public void create() {
        createCustomerService.execute(Customer
                .builder()
                .country("BRAZIL")
                .name("Marcelo")
                .musicStyle("ROCK")
                .build()
        );
    }

}

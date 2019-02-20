package com.marcelsouzav.spotifycustomersave.service;

import com.marcelsouzav.spotifycustomersave.domain.Customer;
import com.marcelsouzav.spotifycustomersave.gateway.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateCustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public UUID execute(Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }

}

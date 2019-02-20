package com.marcelsouzav.spotifycustomersave.gateway.repository;

import com.marcelsouzav.spotifycustomersave.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}

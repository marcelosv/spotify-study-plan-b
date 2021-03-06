package com.marcelsouzav.spotify.customer.gateway.repository;

import com.marcelsouzav.spotify.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

}

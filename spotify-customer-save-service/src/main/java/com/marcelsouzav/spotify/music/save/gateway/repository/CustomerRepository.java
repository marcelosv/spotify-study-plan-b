package com.marcelsouzav.spotify.music.save.gateway.repository;

import com.marcelsouzav.spotify.music.save.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

}

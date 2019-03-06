package com.marcelsouzav.spotify.music.save.gateway.repository;

import com.marcelsouzav.spotify.customer.domain.Music;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MusicRepository extends CrudRepository<Music, UUID> {

}

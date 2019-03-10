package com.marcelsouzav.spotify.music.listener.gateway.repository;

import com.marcelsouzav.spotify.music.listener.domain.Music;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MusicRepository extends CrudRepository<Music, UUID> {

}

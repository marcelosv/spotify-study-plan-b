package com.marcelsouzav.spotify.music.save.service;

import com.marcelsouzav.spotify.music.save.domain.Music;
import com.marcelsouzav.spotify.music.save.gateway.repository.MusicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CreateMusicServiceTest {

    @Mock
    private MusicRepository musicRepository;

    @InjectMocks
    private CreateMusicService createMusicService;

    @Test
    public void create() {
        createMusicService.execute(Music
                .builder()
                .name("Marcelo")
                .uuidCustomer(UUID.randomUUID().toString())
                .build()
        );
    }

}

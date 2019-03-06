package com.marcelsouzav.spotify.customer.service;

import com.marcelsouzav.spotify.json.MusicJson;
import com.marcelsouzav.spotify.music.save.service.CreateMusicListener;
import com.marcelsouzav.spotify.music.save.service.CreateMusicService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CreateMusicListenerTest {

    @Mock
    private CreateMusicService createMusicService;

    @InjectMocks
    private CreateMusicListener createMusicListener;

    @Test
    public void create() throws InterruptedException {

        Mockito.when(createMusicService.execute(org.mockito.Matchers.any())).thenReturn(UUID.randomUUID());

        MusicJson json = MusicJson
                .builder()
                .name("Marcelo")
                .uuidCustomer(UUID.randomUUID().toString())
                .build();

        MusicJson listen = createMusicListener.listen(json);
        Assert.assertNotNull(listen.getUuid());
    }
}

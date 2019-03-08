package com.marcelsouzav.spotify.music.gateway.http;

import com.marcelsouzav.spotify.json.MusicJson;
import com.marcelsouzav.spotify.music.service.SaveMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
public class MusicController {

    @Autowired
    private SaveMusicService saveMusicService;

    @PostMapping("/customers/{uuid}/musics")
    public String create(@PathVariable("uuid") String uuid, @RequestBody MusicJson customerJson) throws ExecutionException, InterruptedException {
        customerJson.setUuidCustomer(uuid);
        return saveMusicService.execute(customerJson);
    }

}

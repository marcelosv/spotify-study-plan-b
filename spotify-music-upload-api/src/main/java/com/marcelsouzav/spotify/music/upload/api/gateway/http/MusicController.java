package com.marcelsouzav.spotify.music.upload.api.gateway.http;

import com.marcelsouzav.spotify.json.MusicUploadJson;
import com.marcelsouzav.spotify.music.upload.api.service.UploadMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
@RequestMapping("/v1")
public class MusicController {

    @Autowired
    private UploadMusicService uploadMusicService;

    @PostMapping("/customer/{uuid}/music/{uuidMusic}")
    public ResponseEntity<?> create(@PathParam("uuid") String uuid,
                                    @PathParam("uuidMusic") String uuidMusic,
                                    @RequestParam("file") MultipartFile file) throws IOException {
        uploadMusicService.execute(MusicUploadJson
                .builder()
                .uuid(uuid)
                .uuidMusic(uuidMusic)
                .file(file)
                .build()
        );

        return ResponseEntity.ok().build();
    }

}

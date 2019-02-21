package com.marcelsouzav.spotify.customer.save.gateway.http;

import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/v1")
public class ListenAPI {

    @RequestMapping("/music")
    @ResponseBody
    public ResponseEntity<InputStreamResource> listenMusic() throws IOException {
        File videoFile = ResourceUtils.getFile("classpath:mp3/01-rappa.mp3");

        long len = Files.size(Paths.get(videoFile.toURI())) ;

        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(videoFile));

        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(len)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + videoFile.getName() + "\"")
                .body(resource);
    }
}

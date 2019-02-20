package com.marcelsouzav.spotifycustomersave.gateway.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(ListenAPI.class)
public class ListenAPITest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void
    test_get_music_with_return_ok() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders
                        .post("/v1/music/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}

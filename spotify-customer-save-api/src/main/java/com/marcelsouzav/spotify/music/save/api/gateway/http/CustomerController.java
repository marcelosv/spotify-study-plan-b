package com.marcelsouzav.spotify.music.save.api.gateway.http;

import com.marcelsouzav.spotify.music.save.api.service.SaveCustomerService;
import com.marcelsouzav.spotify.json.CustomerJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private SaveCustomerService saveCustomerService;

    @PostMapping("/")
    public String create(@RequestBody CustomerJson customerJson) throws ExecutionException, InterruptedException {
        return saveCustomerService.execute(customerJson);
    }


}

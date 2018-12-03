package com.ws.app.init;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @RequestMapping(value = "/login")
    public String hello() {
        return "test file";
    }
}

package com.ws.app.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @RequestMapping(value = "/login")
    public String hello(@RequestBody Map<String,Object> param) {
        return "test file";
    }
}

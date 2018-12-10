package com.ws.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.ws.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @Autowired
    private LoginService loginServiceImpl;

    @RequestMapping(value = "/login")
    public List<Map<String,Object>> hello() {
        return loginServiceImpl.login();
    }
}

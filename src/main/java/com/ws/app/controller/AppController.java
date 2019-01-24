package com.ws.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.ws.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AppController {

    @Autowired
    private LoginService loginServiceImpl;

    @RequestMapping(value = "/login")
    public List<Map<String,Object>> hello(@RequestParam String userNo) {
        return loginServiceImpl.login(userNo);
    }

    @PostMapping(value = "/queryFee")
    public JSONObject queryFee(@RequestBody Map<String,Object> map) {
        return loginServiceImpl.queryFee(map);
    }

    @PostMapping(value = "/writeOff")
    public JSONObject writeOff(@RequestBody Map<String,Object> map) {
        return loginServiceImpl.writeOff(map);
    }



}

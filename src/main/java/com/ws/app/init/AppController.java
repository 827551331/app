package com.ws.app.init;


import com.ws.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

public class AppController {

    @Autowired
    private LoginService loginServiceImpl;

    @RequestMapping(value = "/login")
    public List<Map<String,Object>> hello() {
        return loginServiceImpl.login("");
    }
}

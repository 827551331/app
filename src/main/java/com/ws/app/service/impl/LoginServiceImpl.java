package com.ws.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ws.app.dao.LoginMapper;
import com.ws.app.service.LoginService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {
    private final static Logger log = Logger.getLogger(LoginServiceImpl.class);


    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<Map<String,Object>> login() {
        List<Map<String,Object>> result = loginMapper.login();
        System.out.println(result);
        log.info(result);
        return result;
    }
}

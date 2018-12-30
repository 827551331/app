package com.ws.app.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LoginService {

    public List<Map<String,Object>> login();

    public JSONObject queryFee(Map<String,Object> map);

    public JSONObject writeOff(Map<String,Object> map);
}

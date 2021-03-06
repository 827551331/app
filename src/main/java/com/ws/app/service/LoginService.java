package com.ws.app.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface LoginService {

    public List<Map<String,Object>> login(String userNo);

    public JSONObject queryFee(Map<String,Object> map);

    public JSONObject writeOff(Map<String,Object> map);
}

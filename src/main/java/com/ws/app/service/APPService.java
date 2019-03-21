package com.ws.app.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface APPService {

    public Map<String, Object> login(String userNo);

    public List<Map<String, Object>> query(String consNo);

    public List<Map<String, Object>> downLoad(String consNo);

    public JSONObject upLoad(Map<String, Object> map);

    public int updatePhone(Map<String, Object> map);

}